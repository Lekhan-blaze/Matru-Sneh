# 🌸 Matru-Sneh (ಮಾತೃ-ಸ್ನೇಹ)

> **"Mother's Love"** — An offline-first, bilingual maternal health tracking Android application for rural Karnataka, India.

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white"/>
  <img src="https://img.shields.io/badge/Language-Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white"/>
  <img src="https://img.shields.io/badge/Min%20SDK-API%2021-orange?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Architecture-MVVM-blue?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/AI-Google%20Gemini-4285F4?style=for-the-badge&logo=google&logoColor=white"/>
  <img src="https://img.shields.io/badge/Database-Room%20SQLite-003B57?style=for-the-badge"/>
</p>

---

## 📖 About

**Matru-Sneh** is a fully offline, bilingual pregnancy health companion app designed for rural expectant mothers in Karnataka, India — particularly those with **limited or no internet access**. It serves as a digital alternative to the physical Mother-Child Health card, combining modern Android development with **Google Gemini AI-generated content** to deliver pregnancy milestones, health tracking, and emergency support entirely offline.

> Built as part of the **Android App Development using GenAI** internship at **MindMatrix (MindMatrixEd)**.

---

## ✨ Features

| Module | Description |
|---|---|
| 🧑‍🍼 **Onboarding & Profile Setup** | 3-slide ViewPager2 bilingual onboarding + pregnancy week & name registration |
| 📅 **Weekly Pregnancy Milestones** | AI-generated (Google Gemini) bilingual descriptions for gestational weeks 8–40 |
| 👶 **Foetal Kick Counter** | Debounce-protected tap-to-count kick logger with 7-day summary |
| 🏥 **Checkup Scheduler** | Schedule antenatal visits with date picker, countdown, and reminder notifications |
| 🥗 **Nutrition Tracker** | Daily bilingual food checklist seeded from Room DB with progress tracking |
| 🚨 **Health Alerts** | Danger sign logger with one-tap emergency call to helpline 104 |
| 🔔 **Background Reminders** | WorkManager-powered periodic notifications (checkup, kick, nutrition reset) |

---

## 🤖 Google Gemini AI Integration

Matru-Sneh uses **Google Gemini Generative AI** not as a chatbot, but as a **content intelligence engine** — generating **33 bilingual pregnancy milestone descriptions** (weeks 8–40) in both Kannada (ಕನ್ನಡ) and English, embedded directly into the offline app.

| Week | English | Kannada |
|---|---|---|
| 8 | Baby is the size of a kidney bean. Heart is beating! | ಮಗು ಮೂತ್ರಪಿಂಡ ಹಣ್ಣಿನ ಗಾತ್ರ. ಹೃದಯ ಬಡಿಯುತ್ತಿದೆ! |
| 20 | Baby can hear your voice now! Halfway there! | ಮಗು ನಿಮ್ಮ ದನಿ ಕೇಳಬಲ್ಲದು! ಅರ್ಧ ದಾರಿ ಬಂದಿದೆ! |
| 40 | Your baby is here! Congratulations! Rest well. | ನಿಮ್ಮ ಮಗು ಬಂದಿದೆ! ಅಭಿನಂದನೆ! ಚೆನ್ನಾಗಿ ವಿಶ್ರಾಂತಿ ತೆಗೆಕೊಳ್ಳಿ. |

---

## 🏗️ Architecture

This app follows the **MVVM (Model-View-ViewModel)** architectural pattern with a strict unidirectional data flow:

```
UI (Fragment/Activity)
    ↓ observes
ViewModel (LiveData)
    ↓ calls
Repository
    ↓ queries
Room DAO (SQLite)
```

No DAO calls are ever made from the UI layer — enforcing clean separation of concerns throughout.

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| **Kotlin** | Primary programming language |
| **Android SDK (API 21–34)** | Target platform — supports 98%+ of Android devices |
| **MVVM + ViewBinding** | Architectural pattern with type-safe view access |
| **Room Database (KSP)** | Local offline data persistence — 6 entities |
| **WorkManager** | Periodic background task scheduling |
| **LiveData + Kotlin Coroutines** | Reactive UI and asynchronous operations |
| **Navigation Component** | Fragment navigation and back stack management |
| **Jetpack ViewModel** | Lifecycle-aware state management |
| **Google Gemini (GenAI)** | AI-powered bilingual milestone content generation |
| **ViewPager2** | Onboarding screen carousel (3 slides) |
| **Material Design 3** | UI components and theming |
| **WorkManager BootReceiver** | Re-schedule workers after device reboot |

---

## 🗄️ Room Database Schema

The app uses a **6-table Room Database (Version 2)** — no network required.

| Table | Key Columns | Purpose |
|---|---|---|
| `mother_profile` | id, name, pregnancyWeekStart (ms) | Single-row user profile |
| `kick_log` | id, timestampMs, date | Each foetal kick event |
| `checkup` | id, type, dateMs, notifTag | Scheduled antenatal visits |
| `nutrition_log` | id, date, itemIndex, checked | Daily food checklist state |
| `danger_log` | id, signs, timestampMs | Emergency danger sign events |
| `food_item` | id, nameEn, nameKn, emoji | Bilingual food master (seeded on install) |

> **Food items** are seeded via `RoomDatabase.Callback` on first install — 10 locally relevant foods with bilingual names and emoji icons, stored in the DB and rendered dynamically.

---

## ⚙️ Background Workers (WorkManager)

Three periodic workers run silently in the background:

| Worker | Interval | Action |
|---|---|---|
| `CheckupReminderWorker` | Every 12 hours | Notifies the user of upcoming antenatal checkups |
| `KickAlertWorker` | Every 2 hours | Prompts the user to track foetal movement |
| `NutritionResetWorker` | Daily at midnight | Resets the daily nutrition checklist |

A `BootReceiver` (`RECEIVE_BOOT_COMPLETED`) re-registers all workers after device restart.

---

## 📦 Project Structure

```
com.matrusineh.health
│
├── MatruSnehApp.kt                  → Application class; WorkManager scheduler; PrefHelper sync
│
├── data/
│   ├── db/
│   │   ├── AppDatabase.kt           → Room DB (version 2); FoodItem seed callback
│   │   ├── MotherProfile.kt / Dao   → User name + pregnancy start timestamp
│   │   ├── Kick.kt / KickDao        → Foetal kick events with timestamps
│   │   ├── Checkup.kt / CheckupDao  → Antenatal checkup type and date
│   │   ├── NutritionLog.kt / Dao    → Daily food item checked state
│   │   ├── DangerLog.kt / Dao       → Health danger sign log
│   │   └── FoodItem.kt / Dao        → Bilingual food item master (seeded)
│   ├── model/
│   │   └── WeeklyMilestone.kt       → GenAI-generated milestone data (weeks 8–40)
│   └── repository/
│       ├── ProfileRepository.kt
│       ├── KickRepository.kt
│       ├── CheckupRepository.kt
│       ├── NutritionRepository.kt
│       ├── DangerLogRepository.kt
│       └── FoodItemRepository.kt
│
├── ui/
│   ├── OnboardingActivity.kt        → 3-slide ViewPager2 bilingual onboarding
│   ├── ProfileSetupActivity.kt      → Name + pregnancy week input
│   ├── MainActivity.kt              → Dashboard: name, week, milestone card, bottom nav
│   ├── KickCounterFragment.kt       → Kick logging + weekly summary
│   ├── CheckupFragment.kt           → Date picker + checkup list + countdown card
│   ├── NutritionFragment.kt         → Food checklist + progress bar
│   └── HealthAlertFragment.kt       → Danger sign selection + emergency alert
│
├── viewmodel/
│   ├── ProfileViewModel.kt
│   ├── KickViewModel.kt
│   ├── CheckupViewModel.kt
│   ├── NutritionViewModel.kt
│   └── HealthAlertViewModel.kt
│
├── workers/
│   ├── CheckupReminderWorker.kt     → Periodic: checkup notifications (every 12 hrs)
│   ├── KickAlertWorker.kt           → Periodic: kick count prompt (every 2 hrs)
│   └── NutritionResetWorker.kt      → Periodic: daily nutrition reset (at midnight)
│
└── utils/
    ├── NotificationHelper.kt        → Multi-channel notification builder
    ├── PrefHelper.kt                → SharedPreferences wrapper + DB version sync
    ├── DateUtils.kt                 → Date formatting utilities
    └── BootReceiver.kt              → Reschedule workers after device reboot
```

---

## 🔑 Permissions

| Permission | Purpose |
|---|---|
| `POST_NOTIFICATIONS` | Health reminder notifications (Android 13+) |
| `RECEIVE_BOOT_COMPLETED` | Re-register WorkManager tasks after device restart |
| `SCHEDULE_EXACT_ALARM` | Schedule precise-time alerts |
| `USE_EXACT_ALARM` | Exact alarms on API 33+ |
| `CALL_PHONE` | Emergency helpline calling (104) from danger alert screen |
| `VIBRATE` | Haptic feedback on kick button tap |

---

## 🔧 Build Configuration

| Parameter | Value |
|---|---|
| Language | Kotlin |
| Compile SDK | 34 (Android 14) |
| Min SDK | **21 (Android 5.0 — ~98% device coverage)** |
| Target SDK | 34 |
| Build System | Gradle (Kotlin DSL) |
| Annotation Processor | KSP (Kotlin Symbol Processing) |
| Architecture | MVVM with ViewBinding |
| Database | Room (SQLite), Version 2 |
| Package Name | `com.matrusineh.health` |
| App Version | 1.0 |

---

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- JDK 17+
- Android SDK with API 21–34 installed

### Clone & Run
```bash
git clone https://github.com/Lekhan-blaze/Matru-Sneh.git
cd Matru-Sneh
```
1. Open the project in **Android Studio**
2. Let Gradle sync and resolve dependencies
3. Connect a physical device or launch an emulator (API 21+)
4. Click **Run ▶** or use `Shift + F10`

> ℹ️ No API keys or internet connection are required to run the app.

---

## 🌍 Bilingual Support

Every screen renders content in both **Kannada (ಕನ್ನಡ)** and **English** — from onboarding slides to error messages — making the app fully accessible for rural Karnataka users regardless of primary language.

Design principles applied:
- 📌 Emoji icons as universal visual anchors
- 🔤 Unicode-safe Kannada text rendering
- 🖐️ Large touch targets for easy interaction
- 💬 Concise, culturally appropriate copy

---

## 💡 What Makes This Project Unique

1. **Real GenAI Integration** — Google Gemini was used as a content intelligence engine to generate 33 bilingual pregnancy milestones embedded directly into the offline app — not a chatbot.
2. **Bilingual Kannada-English UI** — A first-of-its-kind design driven by empathy for the rural Karnataka user base.
3. **Widest Device Compatibility** — Min SDK API 21 covers ~98% of all active Android devices, far broader than most comparable projects.
4. **Fully Offline** — Zero network dependency. All data lives in Room (SQLite) on the device.

---

## 📄 License

This project was developed as part of an internship programme at **MindMatrix (MindMatrixEd)**, Bengaluru, Karnataka, India.  
**Track:** Android App Development using GenAI | **Duration:** 15 Weeks (February 2026 – May 2026)

---

<p align="center">Made with ❤️ for rural mothers of Karnataka &nbsp;|&nbsp; ಕರ್ನಾಟಕದ ತಾಯಂದಿರಿಗಾಗಿ ❤️</p>
