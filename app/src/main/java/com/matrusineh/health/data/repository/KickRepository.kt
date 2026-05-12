package com.matrusineh.health.data.repository

import androidx.lifecycle.LiveData
import com.matrusineh.health.data.db.Kick
import com.matrusineh.health.data.db.KickDao
import java.util.Calendar

class KickRepository(private val kickDao: KickDao) {

    fun getKicksToday(): LiveData<List<Kick>> {
        return kickDao.getKicksToday(startOfToday())
    }

    suspend fun logKick(): Long {
        return kickDao.insert(Kick())
    }

    suspend fun countToday(): Int = kickDao.countToday(startOfToday())

    suspend fun countThisHour(): Int {
        val cal = Calendar.getInstance()
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return kickDao.countThisHour(cal.timeInMillis)
    }

    suspend fun countLast2Hours(): Int {
        val twoHoursAgo = System.currentTimeMillis() - (2 * 60 * 60 * 1000L)
        return kickDao.countBetween(twoHoursAgo, System.currentTimeMillis())
    }

    suspend fun resetTodayKicks() {
        kickDao.deleteTodayKicks(startOfToday())
    }

    suspend fun getKicksSince(startMs: Long): List<Kick> = kickDao.getKicksSince(startMs)

    private fun startOfToday(): Long {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return cal.timeInMillis
    }
}
