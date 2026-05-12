package com.matrusineh.health.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CheckupDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(checkup: Checkup): Long

    @Delete
    suspend fun delete(checkup: Checkup)

    @Query("SELECT * FROM checkups WHERE dateEpoch >= :now ORDER BY dateEpoch ASC")
    fun getUpcomingCheckups(now: Long): LiveData<List<Checkup>>

    @Query("SELECT * FROM checkups WHERE dateEpoch >= :now ORDER BY dateEpoch ASC LIMIT 1")
    suspend fun getNextCheckup(now: Long): Checkup?

    @Query("SELECT * FROM checkups WHERE notified = 0 AND dateEpoch > :now")
    suspend fun getUnnotifiedCheckups(now: Long): List<Checkup>

    @Query("UPDATE checkups SET notified = 1 WHERE id = :id")
    suspend fun markNotified(id: Long)
}
