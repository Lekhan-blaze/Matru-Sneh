package com.matrusineh.health.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

object NotificationHelper {
    fun createChannels(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = context.getSystemService(NotificationManager::class.java)

            manager.createNotificationChannel(
                NotificationChannel("checkup_channel", "Checkup Reminders", NotificationManager.IMPORTANCE_HIGH)
            )
            manager.createNotificationChannel(
                NotificationChannel("kick_channel", "Kick Alerts", NotificationManager.IMPORTANCE_HIGH)
            )
            manager.createNotificationChannel(
                NotificationChannel("nutrition_channel", "Nutrition Reminders", NotificationManager.IMPORTANCE_DEFAULT)
            )
        }
    }
}
