package com.matrusineh.health.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NutritionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(log: NutritionLog)

    @Query("SELECT * FROM nutrition_log WHERE date = :date ORDER BY itemIndex ASC")
    fun getForDate(date: String): LiveData<List<NutritionLog>>

    @Query("SELECT * FROM nutrition_log WHERE date = :date ORDER BY itemIndex ASC")
    suspend fun getForDateSync(date: String): List<NutritionLog>

    @Query("DELETE FROM nutrition_log WHERE date = :date")
    suspend fun clearForDate(date: String)

    @Query("DELETE FROM nutrition_log WHERE date != :keepDate")
    suspend fun deleteOldLogs(keepDate: String)
}
