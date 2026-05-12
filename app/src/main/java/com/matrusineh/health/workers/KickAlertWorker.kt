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
import com.matrusineh.health.data.repository.KickRepository

class KickAlertWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {
        val db = AppDatabase.getInstance(applicationContext)
        val repo = KickRepository(db.kickDao())
        val last2Hr = repo.countLast2Hours()
        if (last2Hr < 10) {
            showLowKickNotification()
        }
        return Result.success()
    }

    private fun showLowKickNotification() {
        val manager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("kick_channel", "Kick Alerts", NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
        }
        val notif = NotificationCompat.Builder(applicationContext, "kick_channel")
            .setSmallIcon(R.drawable.ic_alert)
            .setContentTitle(applicationContext.getString(R.string.notif_kick_title))
            .setContentText(applicationContext.getString(R.string.notif_kick_text))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()
        manager.notify(2001, notif)
    }
}
