package com.matrusineh.health.data.repository

import androidx.lifecycle.LiveData
import com.matrusineh.health.data.db.MotherProfile
import com.matrusineh.health.data.db.MotherProfileDao

class ProfileRepository(private val profileDao: MotherProfileDao) {
    fun getProfile(): LiveData<MotherProfile?> = profileDao.getProfile()
    suspend fun getProfileSync(): MotherProfile? = profileDao.getProfileSync()
    suspend fun saveProfile(profile: MotherProfile) = profileDao.insertOrUpdate(profile)
}
