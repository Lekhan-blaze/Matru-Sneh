package com.matrusineh.health.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    private val sdf = SimpleDateFormat("dd MMM yyyy", Locale.US)
    private val todaySdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    fun formatDate(epochMs: Long): String = sdf.format(Date(epochMs))
    fun todayString(): String = todaySdf.format(Date())
    fun startOfToday(): Long {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return cal.timeInMillis
    }
}
