# INTERNSHIP REPORT
## Android App Development Using Generative AI (GenAI)
**Organisation:** MindMatrix (MindMatrixEd)
**Project:** Matru-Sneh — Offline-First Maternal Health Android Application
**Track:** Android App Development using GenAI
**Mode:** Online / Remote
**Duration:** 15 Weeks (February 2026 – May 2026)

---

---

## v. ABOUT THE ORGANISATION

MindMatrix, operating its educational division as **MindMatrixEd**, is an e-learning platform headquartered in **Bengaluru, Karnataka, India**. The organisation delivers industry-aligned training programmes specifically designed for engineering students, bridging the gap between academic learning and real-world industry expectations.

MindMatrixEd provides a structured learning environment through its proprietary Learning Management System accessible at **lms.mindmatrix.io**, and facilitates collaborative peer interaction via its Discord server, **VTU-Frontiers-Official**. The organisation's mission is to empower students with practical skills through hands-on industry experience and mentorship. Its vision centres on creating industry-ready professionals through structured learning and real-world project exposure.

The internship programme through which the present work was conducted falls under the **Android App Development using GenAI** track, which is part of MindMatrix's broader initiative to modernise engineering education through mobile and AI-focused curriculum. The programme coordinator and external mentor assigned was **Mr. Tirumal Mutalikdesai**, whose role encompassed providing technical guidance, conducting performance reviews, and offering continuous mentorship throughout the programme.

### Services Offered by MindMatrix

| Service Area | Description |
|---|---|
| Mobile App Development | Structured training in Android development using Kotlin, AI integration, and modern Jetpack libraries |
| Educational Technology | Modernisation of engineering learning through digital platforms, LMS-based course delivery, and project-based assessment |
| AI Integration Training | Instruction in leveraging AI tools such as ChatGPT, Google Gemini, and Antigravity for development workflows |
| Industry Mentorship | Assignment of industry professionals as mentors for code reviews, technical feedback, and career guidance |
| Community Learning | Peer collaboration through Discord, knowledge sharing, and team-based development simulation |

MindMatrix emphasises experiential learning through hands-on project development, enabling students to apply theoretical knowledge in practical scenarios. The structured progression from beginner to advanced projects ensures gradual skill enhancement while reinforcing core programming and architectural concepts. Continuous assessment through codelabs, milestone reviews, and mentor evaluations ensures consistent progress and measurable growth.

---

---

## vi. OBJECTIVE OF INTERNSHIP

The primary objective of this internship was to design, develop, and deliver a fully functional **Android application using Kotlin** that integrates **Generative AI (Google Gemini)** to provide intelligent, personalised maternal health support — entirely offline — targeting rural communities in Karnataka, India.

### Project: Matru-Sneh (ಮಾತೃ-ಸ್ನೇಹ)

*Matru-Sneh*, meaning **"Mother's Love"** in Kannada, is an offline-first pregnancy health tracking application tailored for rural expectant mothers with limited or no internet access. The application serves as a digital alternative to the physical Mother-Child Health card, delivering AI-assisted weekly pregnancy milestone updates, nutrition tracking, foetal kick counting, antenatal checkup scheduling, and health emergency alerts — all with full bilingual support in Kannada and English.

### Core Objectives

1. **Android App Development:** Build a complete, production-ready Android application in Kotlin following the MVVM (Model-View-ViewModel) architectural pattern with ViewBinding, ensuring clean separation of concerns and lifecycle awareness.

2. **GenAI Content Integration:** Use **Google Gemini Generative AI** to generate bilingual (Kannada + English) pregnancy milestone descriptions covering gestational weeks 8 through 40 — embedding AI-generated content directly into the offline app as a structured data model.

3. **Offline-First Architecture:** Implement a fully offline data layer using **Room Database (SQLite)** with six entities and corresponding DAOs, ensuring the app functions without any internet connection at all times.

4. **Background Intelligence via WorkManager:** Build three periodic background workers:
   - `CheckupReminderWorker` — fires every 12 hours to remind the user of upcoming antenatal checkups
   - `KickAlertWorker` — fires every 2 hours to prompt foetal kick counting sessions
   - `NutritionResetWorker` — resets the daily nutrition checklist at midnight

5. **Bilingual Accessibility:** Design a complete bilingual user interface in Kannada (ಕನ್ನಡ) and English across all screens — onboarding, profile setup, kick counter, checkup scheduler, nutrition tracker, and health alerts.

6. **Feature Module Delivery:** Build and integrate six core modules — Kick Counter, Checkup Scheduler, Nutrition Tracker, Health Alerts, Weekly Milestones, and Onboarding / Profile Setup.

7. **Food Item DB Seeding:** Implement a scalable food item master list seeded into Room Database via a `RoomDatabase.Callback` on first install — storing 10 locally relevant foods with bilingual names and emoji icons.

8. **Industry Best Practices:** Apply professional Android development practices including LiveData observers, Kotlin Coroutines, DiffUtil-based RecyclerView adapters, modular package structure, and strict MVVM enforcement (no DAO calls in the UI layer).

### Technology Stack

| Technology | Purpose |
|---|---|
| Kotlin | Primary programming language |
| Android SDK (API 21–34) | Target platform — supports 98%+ of Android devices |
| MVVM Architecture | Architectural pattern with ViewBinding |
| Room Database (KSP) | Local offline data persistence — 6 entities |
| WorkManager | Periodic background task scheduling |
| LiveData + Kotlin Coroutines | Reactive UI and asynchronous operations |
| ViewBinding | Type-safe, null-safe view access |
| Navigation Component | Fragment navigation and back stack management |
| Jetpack ViewModel | Lifecycle-aware state management |
| Google Gemini (GenAI) | AI-powered bilingual milestone content generation |
| ViewPager2 | Onboarding screen carousel (3 slides) |
| Material Design 3 | UI components and theming |
| WorkManager BootReceiver | Re-schedule workers after device reboot |

---

---

## vii. LEARNING EXPERIENCES

The internship provided a rich, hands-on learning experience spanning modern Android development, Generative AI integration, inclusive design for low-connectivity users, and professional engineering practices. The following are the key learning experiences encountered during the development of Matru-Sneh.

### 1. MVVM Architecture Across Multiple Feature Modules

Implementing the MVVM pattern consistently across all six feature modules was the most foundational learning experience. Each module — Kick Counter, Checkup Scheduler, Nutrition Tracker, Health Alerts, Weekly Milestones, and Profile — has its own dedicated ViewModel, Repository, and Fragment or Activity.

This enforced a clean separation of concerns and taught the reactive approach to UI state management using **LiveData**. The key lesson was that ViewModels survive configuration changes (such as screen rotations) and that Fragments should only observe data — never directly access databases. The entire data flow follows a strict unidirectional pattern: UI → ViewModel → Repository → Room DAO.

### 2. Offline-First Design with Room Database

Designing the Room database schema was a deep engineering exercise. The application's database contains **six entities and DAOs**:

| Entity | Purpose |
|---|---|
| `MotherProfile` | Stores the user's name and LMP-based pregnancy start timestamp |
| `Kick` | Records each foetal kick event with a full timestamp |
| `Checkup` | Stores scheduled antenatal visits with type and date |
| `NutritionLog` | Tracks daily food item checked/unchecked state per date |
| `DangerLog` | Logs emergency health danger sign events |
| `FoodItem` | Static master list of 10 bilingual food items — seeded via DB callback |

Writing efficient Room queries using `suspend` functions and `LiveData` streams, and handling the challenge of zero network dependency while maintaining rich functionality, were central engineering lessons of this internship.

### 3. Food Item DB Seeding via RoomDatabase.Callback

A key scalability improvement implemented during the internship was replacing hard-coded XML checkboxes for the nutrition module with a **database-driven approach using `RoomDatabase.Callback`**. On first install, the `onCreate` callback fires and inserts 10 food items into the `food_item` table via a coroutine on the IO dispatcher.

This approach means food items are stored in the DB, can be queried via LiveData, and the UI renders them dynamically — making the list easily extensible without modifying any XML layout. Each `FoodItem` entity stores an English name, a Kannada name, and an emoji icon, providing a bilingual and visually accessible experience.

### 4. GenAI Integration — Google Gemini for Bilingual Milestone Content

The most distinctive feature of this project is the use of **Google Gemini Generative AI** to generate pregnancy milestone descriptions for gestational weeks 8 through 40 — a total of 33 entries — in both Kannada and English. This was a direct application of GenAI in a domain-specific offline Android context.

The learning involved crafting structured prompts to obtain accurate, medically relevant, and culturally appropriate content, then validating and embedding the AI-generated output into a static `MilestoneData` Kotlin object that is accessible offline. This demonstrated that GenAI is not limited to chat interfaces — it can function as a **content intelligence engine** for structured, domain-specific data.

Sample milestone entries generated by Gemini:

| Week | English | Kannada |
|---|---|---|
| 8 | Baby is the size of a kidney bean. Heart is beating! | ಮಗು ಮೂತ್ರಪಿಂಡ ಹಣ್ಣಿನ ಗಾತ್ರ. ಹೃದಯ ಬಡಿಯುತ್ತಿದೆ! |
| 20 | Baby can hear your voice now! Halfway there! | ಮಗು ನಿಮ್ಮ ದನಿ ಕೇಳಬಲ್ಲದು! ಅರ್ಧ ದಾರಿ ಬಂದಿದೆ! |
| 40 | Your baby is here! Congratulations! Rest well. | ನಿಮ್ಮ ಮಗು ಬಂದಿದೆ! ಅಭಿನಂದನೆ! ಚೆನ್ನಾಗಿ ವಿಶ್ರಾಂತಿ ತೆಗೆಕೊಳ್ಳಿ. |

### 5. Three WorkManager Background Workers

Building and scheduling the three background workers was a practical lesson in Android's modern task scheduling system. The workers are registered in `MatruSnehApp.onCreate()` using `PeriodicWorkRequestBuilder` with `ExistingPeriodicWorkPolicy.KEEP` to prevent duplicate task registration.

- `CheckupReminderWorker` — runs every 12 hours; checks for upcoming checkups and sends a notification
- `KickAlertWorker` — runs every 2 hours; prompts the user to track foetal movement
- `NutritionResetWorker` — uses an `initialDelay` calculated to midnight; resets nutrition logs daily

A `BootReceiver` (`android.intent.action.BOOT_COMPLETED`) re-registers all workers after device restart, ensuring background services persist across reboots.

### 6. Bilingual UI Development — Kannada and English

Every screen in the application renders content in both Kannada script and English — from the three onboarding slides to error messages in the profile setup form. Building a bilingual interface required handling Unicode rendering for Kannada, managing all content through `strings.xml`, and framing content in a culturally appropriate manner for rural Karnataka users.

The bilingual approach significantly influenced UX decisions — using emoji icons as universal visual anchors, keeping button text concise, and ensuring error messages are understood regardless of the user's primary language.

### 7. Android Notification System and Permission Management

Implementing the multi-channel notification system required a deep understanding of the Android permission model. The `NotificationHelper` creates separate notification channels for checkup reminders, kick alerts, and nutrition resets. Runtime permissions handled include:

- `POST_NOTIFICATIONS` (Android 13 / API 33+)
- `SCHEDULE_EXACT_ALARM` and `USE_EXACT_ALARM`
- `CALL_PHONE` for emergency helpline calling
- `VIBRATE` for kick button haptic feedback
- `RECEIVE_BOOT_COMPLETED` for worker re-registration

Navigating Android's evolving permission model consistently across API levels 21 through 34 was a significant practical challenge that deepened understanding of the Android security model.

### 8. SharedPreferences and DB Version Synchronisation

A key debugging insight was resolving a mismatch between `SharedPreferences` flags and the Room DB state. When the DB version was bumped from 1 to 2 (to add the `FoodItem` entity), `fallbackToDestructiveMigration()` wiped the existing DB. However, SharedPreferences still held `profile_set = true`, causing the app to skip profile setup and show a blank dashboard.

The fix was implementing a `syncWithDbVersion()` method in `PrefHelper` that compares a stored DB version key against the current version constant — and resets all onboarding and profile flags if a mismatch is detected. This is called in `MatruSnehApp.onCreate()` before any navigation occurs.

---

---

## viii. LEARNING OUTCOMES

Upon completion of this internship, the following concrete learning outcomes were achieved across technical proficiency, design thinking, and professional development.

### Technical Outcomes

**1. Android MVVM Architecture Proficiency**
Can independently design and implement a fully modular MVVM Android application — with ViewModels, Repositories, LiveData, Room DB, and Fragments — across multiple feature domains, following strict separation of concerns.

**2. Google Gemini GenAI Integration**
Gained hands-on experience crafting structured prompts for Google Gemini to generate domain-specific, bilingual, and contextually accurate content for a health application. Learned to embed AI-generated content into offline mobile apps as structured Kotlin data objects.

**3. Offline-First Android App Design**
Mastered the principles and implementation of offline-first apps using Room Database with six entities, SharedPreferences for lightweight state, and local-only data management — eliminating all network dependencies.

**4. WorkManager and Background Processing**
Proficient in scheduling periodic background tasks using `PeriodicWorkRequestBuilder`, managing `ExistingPeriodicWorkPolicy`, and persisting workers across device reboots via `BroadcastReceiver`.

**5. Kotlin Coroutines and LiveData**
Developed strong practical knowledge of Kotlin Coroutines (`suspend`, `launch`, `lifecycleScope`, `viewModelScope`), the IO dispatcher, and LiveData for writing asynchronous, non-blocking, lifecycle-aware Android code.

**6. Android Build System and Dependency Management**
Gained hands-on experience with Gradle Kotlin DSL, KSP annotation processing, version catalog (`libs.versions.toml`), resolving JDK conflicts (`jlink` / `JAVA_HOME` issues), and managing DB version migrations — professional-level build system knowledge.

**7. Jetpack Libraries Ecosystem**
Hands-on proficiency with: Room, WorkManager, Navigation Component, ViewPager2, LiveData, ViewModel, ViewBinding, RecyclerView with DiffUtil, Material Design 3 Components, and ConstraintLayout.

**8. Room DB Schema Design and Seeding**
Designed a normalised six-table Room DB schema. Implemented `RoomDatabase.Callback` for first-install food item seeding — a scalable, production-standard approach to static data population.

**9. Android Permission Model**
Thorough understanding of runtime permissions, `ActivityResultContracts`, and consistent permission handling across Android API levels 21 through 34.

### Design and Product Outcomes

**10. Inclusive and Accessible Design**
Developed a strong appreciation for inclusive design — building for users with different language backgrounds, literacy levels, and digital access. Bilingual Kannada-English UI, emoji-first navigation, large touch targets, and simplified flows became central design principles.

**11. Social Impact Engineering**
Gained experience building technology with genuine social purpose — understanding how product decisions (offline-first, bilingual, low-data, wide API support) directly improve usability for rural and marginalised communities.

### Professional Outcomes

**12. End-to-End Product Ownership**
Owned the complete lifecycle of the Matru-Sneh app — from architecture design and database schema through feature implementation, build troubleshooting, and delivery — experiencing the full software development lifecycle.

**13. Debugging and Problem-Solving Under Constraints**
Resolved complex issues including Gradle JDK conflicts (`jlink` executable errors), Room DB migration side effects on SharedPreferences state, and WorkManager duplicate scheduling — developing systematic debugging skills.

**14. Code Quality and Maintainability**
Learned to write clean, reviewable, well-structured Kotlin code with meaningful naming conventions, modular package design, and proper lifecycle management — resulting in a codebase that is testable and extensible.

---

---

## ix. CONCLUSION / SUMMARY

This internship at **MindMatrix** has been one of the most technically enriching and personally meaningful experiences of my academic journey. Working on **Matru-Sneh** — an offline-first, bilingual maternal health Android application for rural Karnataka — provided complete exposure to every phase of real-world mobile product development, from architecture design through feature delivery and debugging.

### What Was Built and Delivered

The Matru-Sneh application was successfully developed as a production-ready Android app with the following delivered components:

- **6 fully functional modules:** Kick Counter, Checkup Scheduler, Nutrition Tracker, Health Alerts, Weekly Milestones, and Onboarding / Profile Setup
- **Full offline capability** using a six-table Room Database — zero internet required
- **Google Gemini GenAI-powered content** — 33 bilingual pregnancy milestone descriptions (weeks 8–40) generated using Generative AI and embedded offline
- **3 WorkManager background services** — CheckupReminderWorker (12 hrs), KickAlertWorker (2 hrs), NutritionResetWorker (daily midnight)
- **Bilingual UI** — complete Kannada + English interface across all screens for maximum rural accessibility
- **Food Item DB Seeding** — 10 locally relevant food items seeded via `RoomDatabase.Callback` on first install, with bilingual names and emoji icons
- **MVVM architecture** — clean separation across 5 ViewModels, 5 Repositories, 6 Room entities, and 5 Fragments/Activities
- **Notification system** — multi-channel notification infrastructure with proper runtime permission handling across API 21–34
- **Smooth 3-slide onboarding** — built with ViewPager2 and a custom RecyclerView adapter
- **Profile setup** — collects user name and current pregnancy week (4–42), persisted to Room DB

### What Makes This Project Distinctive

Compared to standard Android internship projects, Matru-Sneh stands out in three key ways:

**1. Real GenAI Integration in the App:** Google Gemini was not just used as a coding assistant — it was used to generate structured, domain-specific, bilingual content (33 pregnancy milestones in Kannada + English) that is embedded directly into the offline app. This is a genuine GenAI-powered feature, not a chatbot.

**2. Bilingual Kannada-English UI:** Every screen renders content in both Kannada script and English — a first-of-its-kind design decision driven by empathy for the rural Karnataka user base. This required Unicode handling, culturally appropriate content, and icon-first design.

**3. Widest Device Compatibility:** With a minimum SDK of API 21 (Android 5.0), the app supports approximately 98%+ of active Android devices — significantly broader than comparable projects targeting API 26+.

### Broader Reflections

Beyond the technical achievements, this internship demonstrated the profound responsibility that comes with building technology for real people. Matru-Sneh is not merely a portfolio project — it is a potential digital health tool for rural mothers in Karnataka who lack consistent access to antenatal care information. Every design decision — offline-first, bilingual, emoji-based icons, simplified navigation — was made with this user in mind.

The integration of Generative AI through Google Gemini opened a new perspective on what GenAI can do in mobile applications: it is a powerful content intelligence engine capable of generating culturally relevant, medically informed, and linguistically diverse content at scale — content that would be impractical to produce manually.

### Key Takeaways

1. Real-world Android development requires deep knowledge of the Jetpack ecosystem and Android's evolving API and permission landscape
2. Generative AI is a powerful tool for structured content generation, personalisation, and domain-specific intelligence — even in offline applications
3. The best technology is built with empathy — understanding who uses it, where they live, what language they speak, and what connectivity they have
4. Product ownership — from architecture to delivery — is the fastest path to genuine engineering growth

This internship at MindMatrix has strengthened my foundation in Android development, ignited a lasting interest in AI-powered mobile applications, and instilled a commitment to building technology that is not only technically excellent but truly impactful for the people it serves.

---

---

## x. ATTACHMENTS

### Attachment A: Project Package Structure

```
com.matrusineh.health
│
├── MatruSnehApp.kt                  → Application class; WorkManager scheduler; PrefHelper sync
│
├── data/
│   ├── db/
│   │   ├── AppDatabase.kt           → Room DB (version 2); FoodItem seed callback
│   │   ├── MotherProfile.kt         → Entity: user name + pregnancy start timestamp
│   │   ├── MotherProfileDao.kt      → DAO: profile insert/query
│   │   ├── Kick.kt                  → Entity: foetal kick event with timestamp
│   │   ├── KickDao.kt               → DAO: kick queries by date/hour
│   │   ├── Checkup.kt               → Entity: antenatal checkup type and date
│   │   ├── CheckupDao.kt            → DAO: upcoming checkup queries
│   │   ├── NutritionLog.kt          → Entity: daily food item checked state
│   │   ├── NutritionDao.kt          → DAO: nutrition log by date
│   │   ├── DangerLog.kt             → Entity: health danger sign log
│   │   ├── DangerLogDao.kt          → DAO: danger log insert/query
│   │   ├── FoodItem.kt              → Entity: bilingual food item master (id, nameEn, nameKn, emoji)
│   │   └── FoodItemDao.kt           → DAO: food item insert/query
│   ├── model/
│   │   └── WeeklyMilestone.kt       → GenAI-generated milestone data (weeks 8–40, bilingual)
│   └── repository/
│       ├── ProfileRepository.kt
│       ├── KickRepository.kt
│       ├── CheckupRepository.kt
│       ├── NutritionRepository.kt
│       ├── DangerLogRepository.kt
│       └── FoodItemRepository.kt    → Repository for DB-seeded food items
│
├── ui/
│   ├── OnboardingActivity.kt        → 3-slide ViewPager2 bilingual onboarding
│   ├── ProfileSetupActivity.kt      → Name + pregnancy week input; saves to Room DB
│   ├── MainActivity.kt              → Dashboard: name, week, milestone card, bottom nav
│   ├── KickCounterFragment.kt       → Debounce-protected kick logging + weekly summary
│   ├── CheckupFragment.kt           → Date picker + checkup list + countdown card
│   ├── NutritionFragment.kt         → Food checklist + progress bar + completion message
│   ├── HealthAlertFragment.kt       → Danger sign selection + emergency alert
│   ├── CheckupAdapter.kt            → DiffUtil RecyclerView adapter for checkups
│   └── WeeklyKickAdapter.kt         → RecyclerView adapter for 7-day kick summary
│
├── viewmodel/
│   ├── ProfileViewModel.kt          → Profile save; current week calculation
│   ├── KickViewModel.kt             → Kick count logic; debounce; session management
│   ├── CheckupViewModel.kt          → Checkup CRUD; countdown days logic
│   ├── NutritionViewModel.kt        → Nutrition log + foodItems LiveData from DB
│   └── HealthAlertViewModel.kt      → Danger sign log management
│
├── workers/
│   ├── CheckupReminderWorker.kt     → Periodic: checkup notifications (every 12 hours)
│   ├── KickAlertWorker.kt           → Periodic: kick count prompt (every 2 hours)
│   └── NutritionResetWorker.kt      → Periodic: daily nutrition log reset (at midnight)
│
└── utils/
    ├── NotificationHelper.kt        → Multi-channel notification builder
    ├── PrefHelper.kt                → SharedPreferences wrapper + DB version sync
    ├── DateUtils.kt                 → Date formatting utilities
    └── BootReceiver.kt              → Reschedule workers after device reboot
```

---

### Attachment B: Room Database Schema

| Table | Key Columns | Purpose |
|---|---|---|
| `mother_profile` | id, name, pregnancyWeekStart (ms) | Single-row user profile |
| `kick_log` | id, timestampMs, date | Each foetal kick event |
| `checkup` | id, type, dateMs, notifTag | Scheduled antenatal visits |
| `nutrition_log` | id, date, itemIndex, checked | Daily food checklist state |
| `danger_log` | id, signs, timestampMs | Emergency danger sign events |
| `food_item` | id, nameEn, nameKn, emoji | Bilingual food master (seeded) |

---

### Attachment C: Permissions Used

| Permission | Purpose |
|---|---|
| `POST_NOTIFICATIONS` | Show health reminder notifications (Android 13+) |
| `RECEIVE_BOOT_COMPLETED` | Re-register WorkManager tasks after device restart |
| `SCHEDULE_EXACT_ALARM` | Schedule precise-time alerts |
| `USE_EXACT_ALARM` | Required for exact alarms on API 33+ |
| `CALL_PHONE` | Emergency helpline calling (104) from danger alert screen |
| `VIBRATE` | Haptic feedback on kick button tap |

---

### Attachment D: Build Configuration

| Parameter | Value |
|---|---|
| Language | Kotlin |
| Compile SDK | 34 (Android 14) |
| Min SDK | 21 (Android 5.0 — 98%+ device coverage) |
| Target SDK | 34 |
| Build System | Gradle (Kotlin DSL) |
| Annotation Processor | KSP (Kotlin Symbol Processing) |
| Architecture | MVVM with ViewBinding |
| Database | Room (SQLite), Version 2 |
| App Version | 1.0 |
| Package Name | com.matrusineh.health |

---

### Attachment E: GenAI Milestone Coverage

Google Gemini was used to generate bilingual pregnancy milestone descriptions for all gestational weeks from 8 to 40 — a total of **33 bilingual entries** covering baby development stages in both Kannada (ಕನ್ನಡ) and English. These are stored as a static `MilestoneData` Kotlin object and are accessible fully offline.

| Week Range | Milestones Covered |
|---|---|
| Weeks 8–13 | First trimester development (organs, heart, fingers) |
| Weeks 14–19 | Second trimester growth (movement, senses, bones) |
| Weeks 20–27 | Mid-pregnancy (hearing, vision, brain activity) |
| Weeks 28–35 | Third trimester (lungs, fat, sleep cycles) |
| Weeks 36–40 | Full term preparation and birth |

---

*End of Internship Report*
*Organisation: MindMatrix (MindMatrixEd) | Track: Android App Development using GenAI | 2026*
