package com.matrusineh.health.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.matrusineh.health.data.db.AppDatabase
import com.matrusineh.health.data.db.MotherProfile
import com.matrusineh.health.data.repository.ProfileRepository
import kotlinx.coroutines.launch

class ProfileViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = ProfileRepository(AppDatabase.getInstance(app).motherProfileDao())

    val profile: LiveData<MotherProfile?> = repo.getProfile()

    fun saveProfile(name: String, pregnancyWeek: Int) {
        val weekStartMs = System.currentTimeMillis() - ((pregnancyWeek - 1) * 7 * 24 * 3600 * 1000L)
        viewModelScope.launch {
            repo.saveProfile(MotherProfile(name = name, pregnancyWeekStart = weekStartMs))
        }
    }

    fun getCurrentWeek(profile: MotherProfile): Int {
        val msElapsed = System.currentTimeMillis() - profile.pregnancyWeekStart
        val week = (msElapsed / (7L * 24 * 3600 * 1000)).toInt() + 1
        return week.coerceIn(1, 42)
    }
}

class ProfileViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return ProfileViewModel(app) as T
    }
}
