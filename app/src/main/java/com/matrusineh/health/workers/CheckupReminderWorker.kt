package com.matrusineh.health.workers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.matrusineh.health.R
import com.matrusineh.health.data.db.AppDatabase
import com.matrusineh.health.data.repository.CheckupRepository
import java.util.concurrent.TimeUnit

class CheckupReminderWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {
        val db = AppDatabase.getInstance(applicationContext)
        val repo = CheckupRepository(db.checkupDao())
        val checkups = repo.getUnnotifiedCheckups()
        val now = System.currentTimeMillis()
        val oneDayMs = TimeUnit.DAYS.toMillis(1)

        checkups.forEach { checkup ->
            val diff = checkup.dateEpoch - now
            if (diff in 0..oneDayMs * 2) {
                showNotification(checkup.type, checkup.id.toInt())
                repo.markNotified(checkup.id)
            }
        }
        return Result.success()
    }

    private fun showNotification(type: String, id: Int) {
        val manager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("checkup_channel",
                applicationContext.getString(R.string.notification_channel_name),
                NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
        }
        val notif = NotificationCompat.Builder(applicationContext, "checkup_channel")
            .setSmallIcon(R.drawable.ic_checkup)
            .setContentTitle(applicationContext.getString(R.string.notif_checkup_title))
            .setContentText(applicationContext.getString(R.string.notif_checkup_text, type))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()
        manager.notify(id, notif)
    }
}
