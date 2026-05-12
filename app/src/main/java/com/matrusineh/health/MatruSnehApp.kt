package com.matrusineh.health

import android.app.Application
import android.content.Context
import androidx.work.*
import com.matrusineh.health.utils.NotificationHelper
import com.matrusineh.health.workers.CheckupReminderWorker
import com.matrusineh.health.workers.KickAlertWorker
import com.matrusineh.health.workers.NutritionResetWorker
import java.util.Calendar
import java.util.concurrent.TimeUnit

class MatruSnehApp : Application() {

    override fun onCreate() {
        super.onCreate()
        PrefHelper.syncWithDbVersion(this)   // ← reset prefs if DB was wiped
        NotificationHelper.createChannels(this)
        scheduleWorkers(this)
    }

    companion object {
        fun scheduleWorkers(context: Context) {
            val workManager = WorkManager.getInstance(context)

            // Checkup reminder — runs every 12 hours
            val checkupRequest = PeriodicWorkRequestBuilder<CheckupReminderWorker>(12, TimeUnit.HOURS)
                .setConstraints(Constraints.Builder()
                    .setRequiresBatteryNotLow(false)
                    .build())
                .build()
            workManager.enqueueUniquePeriodicWork(
                "checkup_reminder",
                ExistingPeriodicWorkPolicy.KEEP,
                checkupRequest
            )

            // Kick alert — runs every 2 hours
            val kickRequest = PeriodicWorkRequestBuilder<KickAlertWorker>(2, TimeUnit.HOURS)
                .build()
            workManager.enqueueUniquePeriodicWork(
                "kick_alert",
                ExistingPeriodicWorkPolicy.KEEP,
                kickRequest
            )

            // Nutrition reset — runs once a day at midnight
            val now = Calendar.getInstance()
            val midnight = Calendar.getInstance().apply {
                add(Calendar.DAY_OF_YEAR, 1)
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
            val initialDelay = midnight.timeInMillis - now.timeInMillis

            val nutritionRequest = PeriodicWorkRequestBuilder<NutritionResetWorker>(1, TimeUnit.DAYS)
                .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
                .build()
            workManager.enqueueUniquePeriodicWork(
                "nutrition_reset",
                ExistingPeriodicWorkPolicy.KEEP,
                nutritionRequest
            )
        }
    }
}
