package com.matrusineh.health.utils

import android.content.Context

object PrefHelper {
    private const val PREF_NAME = "matru_sneh_prefs"
    private const val KEY_ONBOARDING_DONE = "onboarding_done"
    private const val KEY_PROFILE_SET = "profile_set"
    private const val KEY_DB_VERSION = "db_version"
    private const val CURRENT_DB_VERSION = 2  // ← bump this whenever DB version changes

    /** Call this in Application.onCreate() to reset prefs if DB was wiped */
    fun syncWithDbVersion(ctx: Context) {
        val prefs = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val savedVersion = prefs.getInt(KEY_DB_VERSION, -1)
        if (savedVersion != CURRENT_DB_VERSION) {
            // DB was wiped — reset all flags so user goes through setup again
            prefs.edit()
                .putBoolean(KEY_ONBOARDING_DONE, false)
                .putBoolean(KEY_PROFILE_SET, false)
                .putInt(KEY_DB_VERSION, CURRENT_DB_VERSION)
                .apply()
        }
    }

    fun isOnboardingDone(ctx: Context): Boolean =
        ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .getBoolean(KEY_ONBOARDING_DONE, false)

    fun setOnboardingDone(ctx: Context) {
        ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit().putBoolean(KEY_ONBOARDING_DONE, true).apply()
    }

    fun isProfileSet(ctx: Context): Boolean =
        ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .getBoolean(KEY_PROFILE_SET, false)

    fun setProfileSet(ctx: Context) {
        ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit().putBoolean(KEY_PROFILE_SET, true).apply()
    }
}

