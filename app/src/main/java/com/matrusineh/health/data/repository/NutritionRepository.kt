package com.matrusineh.health.data.repository

import androidx.lifecycle.LiveData
import com.matrusineh.health.data.db.NutritionLog
import com.matrusineh.health.data.db.NutritionDao

class NutritionRepository(private val nutritionDao: NutritionDao) {

    fun getForDate(date: String): LiveData<List<NutritionLog>> =
        nutritionDao.getForDate(date)

    suspend fun setItemChecked(date: String, itemIndex: Int, checked: Boolean) {
        nutritionDao.insert(NutritionLog(date = date, itemIndex = itemIndex, checked = checked))
    }

    suspend fun getForDateSync(date: String): List<NutritionLog> =
        nutritionDao.getForDateSync(date)

    suspend fun clearForDate(date: String) = nutritionDao.clearForDate(date)

    suspend fun deleteOldLogs(keepDate: String) = nutritionDao.deleteOldLogs(keepDate)
}
