package com.matrusineh.health.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.matrusineh.health.MatruSnehApp

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            MatruSnehApp.scheduleWorkers(context)
        }
    }
}
