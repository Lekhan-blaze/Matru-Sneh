package com.matrusineh.health.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface KickDao {
    @Insert
    suspend fun insert(kick: Kick): Long

    @Query("SELECT * FROM kicks WHERE timestamp >= :startOfDay ORDER BY timestamp DESC")
    fun getKicksToday(startOfDay: Long): LiveData<List<Kick>>

    @Query("SELECT COUNT(*) FROM kicks WHERE timestamp >= :startOfDay")
    suspend fun countToday(startOfDay: Long): Int

    @Query("SELECT COUNT(*) FROM kicks WHERE timestamp >= :startOfHour")
    suspend fun countThisHour(startOfHour: Long): Int

    @Query("SELECT COUNT(*) FROM kicks WHERE timestamp >= :start AND timestamp <= :end")
    suspend fun countBetween(start: Long, end: Long): Int

    @Query("DELETE FROM kicks WHERE timestamp >= :startOfDay")
    suspend fun deleteTodayKicks(startOfDay: Long)

    @Query("SELECT * FROM kicks WHERE timestamp >= :start ORDER BY timestamp ASC")
    suspend fun getKicksSince(start: Long): List<Kick>
}
