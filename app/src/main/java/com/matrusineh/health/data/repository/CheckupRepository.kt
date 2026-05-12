package com.matrusineh.health.data.repository

import androidx.lifecycle.LiveData
import com.matrusineh.health.data.db.Checkup
import com.matrusineh.health.data.db.CheckupDao

class CheckupRepository(private val checkupDao: CheckupDao) {

    fun getUpcomingCheckups(): LiveData<List<Checkup>> {
        return checkupDao.getUpcomingCheckups(System.currentTimeMillis())
    }

    suspend fun addCheckup(checkup: Checkup): Long = checkupDao.insert(checkup)

    suspend fun deleteCheckup(checkup: Checkup) = checkupDao.delete(checkup)

    suspend fun getNextCheckup(): Checkup? = checkupDao.getNextCheckup(System.currentTimeMillis())

    suspend fun getUnnotifiedCheckups(): List<Checkup> =
        checkupDao.getUnnotifiedCheckups(System.currentTimeMillis())

    suspend fun markNotified(id: Long) = checkupDao.markNotified(id)
}
