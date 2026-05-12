package com.matrusineh.health.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.matrusineh.health.data.db.AppDatabase
import com.matrusineh.health.data.db.Checkup
import com.matrusineh.health.data.repository.CheckupRepository
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class CheckupViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = CheckupRepository(AppDatabase.getInstance(app).checkupDao())

    val upcomingCheckups = repo.getUpcomingCheckups()

    private val _nextCheckupDays = MutableLiveData<Long?>()
    val nextCheckupDays: LiveData<Long?> = _nextCheckupDays

    init { refreshNext() }

    fun addCheckup(type: String, dateEpoch: Long) {
        viewModelScope.launch {
            repo.addCheckup(Checkup(type = type, dateEpoch = dateEpoch))
            refreshNext()
        }
    }

    fun deleteCheckup(checkup: Checkup) {
        viewModelScope.launch {
            repo.deleteCheckup(checkup)
            refreshNext()
        }
    }

    private fun refreshNext() {
        viewModelScope.launch {
            val next = repo.getNextCheckup()
            if (next != null) {
                val diffMs = next.dateEpoch - System.currentTimeMillis()
                _nextCheckupDays.value = TimeUnit.MILLISECONDS.toDays(diffMs).coerceAtLeast(0)
            } else {
                _nextCheckupDays.value = null
            }
        }
    }
}

class CheckupViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return CheckupViewModel(app) as T
    }
}
