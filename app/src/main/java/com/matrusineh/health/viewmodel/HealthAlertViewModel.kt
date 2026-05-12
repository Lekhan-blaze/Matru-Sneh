package com.matrusineh.health.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.matrusineh.health.data.db.AppDatabase
import com.matrusineh.health.data.repository.DangerLogRepository
import kotlinx.coroutines.launch

class HealthAlertViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = DangerLogRepository(AppDatabase.getInstance(app).dangerLogDao())

    private val _selectedSigns = MutableLiveData<Set<Int>>(emptySet())
    val selectedSigns: LiveData<Set<Int>> = _selectedSigns

    val showRedAlert: LiveData<Boolean> = _selectedSigns.map { it.isNotEmpty() }

    fun toggleSign(index: Int, checked: Boolean) {
        val current = _selectedSigns.value?.toMutableSet() ?: mutableSetOf()
        if (checked) current.add(index) else current.remove(index)
        _selectedSigns.value = current
        if (current.isNotEmpty()) {
            viewModelScope.launch {
                repo.logDangerSign(current.joinToString(","))
            }
        }
    }

    fun clearAll() {
        _selectedSigns.value = emptySet()
    }
}

class HealthAlertViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return HealthAlertViewModel(app) as T
    }
}
