package com.matrusineh.health.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MotherProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(profile: MotherProfile)

    @Query("SELECT * FROM mother_profile WHERE id = 1")
    fun getProfile(): LiveData<MotherProfile?>

    @Query("SELECT * FROM mother_profile WHERE id = 1")
    suspend fun getProfileSync(): MotherProfile?
}
