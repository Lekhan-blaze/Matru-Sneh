package com.matrusineh.health.data.db

import androidx.room.*

@Dao
interface DangerLogDao {
    @Insert
    suspend fun insert(log: DangerLog)

    @Query("SELECT * FROM danger_log ORDER BY timestamp DESC")
    suspend fun getAll(): List<DangerLog>
}
