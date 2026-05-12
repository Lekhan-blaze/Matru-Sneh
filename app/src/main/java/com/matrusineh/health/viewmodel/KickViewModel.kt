package com.matrusineh.health.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.matrusineh.health.data.db.AppDatabase
import com.matrusineh.health.data.repository.KickRepository
import kotlinx.coroutines.launch

class KickViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = KickRepository(AppDatabase.getInstance(app).kickDao())

    val kicksToday = repo.getKicksToday()

    private val _todayCount = MutableLiveData(0)
    val todayCount: LiveData<Int> = _todayCount

    private val _hourCount = MutableLiveData(0)
    val hourCount: LiveData<Int> = _hourCount

    private val _last2HrCount = MutableLiveData(0)
    val last2HrCount: LiveData<Int> = _last2HrCount

    private val _showLowAlert = MutableLiveData(false)
    val showLowAlert: LiveData<Boolean> = _showLowAlert

    // Debounce: track last tap timestamp
    private var lastKickTimestamp = 0L
    private val DEBOUNCE_MS = 2000L

    init {
        refreshCounts()
    }

    fun logKick(): Boolean {
        val now = System.currentTimeMillis()
        if (now - lastKickTimestamp < DEBOUNCE_MS) return false
        lastKickTimestamp = now
        viewModelScope.launch {
            repo.logKick()
            refreshCounts()
        }
        return true
    }

    fun refreshCounts() {
        viewModelScope.launch {
            _todayCount.value = repo.countToday()
            _hourCount.value = repo.countThisHour()
            val last2 = repo.countLast2Hours()
            _last2HrCount.value = last2
            _showLowAlert.value = last2 < 10
        }
    }

    fun resetTodayKicks() {
        viewModelScope.launch {
            repo.resetTodayKicks()
            refreshCounts()
        }
    }

    suspend fun getWeeklySummary(): List<Pair<String, Float>> {
        val dayNames = listOf("Sun","Mon","Tue","Wed","Thu","Fri","Sat")
        val result = mutableListOf<Pair<String, Float>>()
        val cal = java.util.Calendar.getInstance()
        // Go back 7 days
        for (i in 6 downTo 0) {
            val dayCal = cal.clone() as java.util.Calendar
            dayCal.add(java.util.Calendar.DAY_OF_YEAR, -i)
            dayCal.set(java.util.Calendar.HOUR_OF_DAY, 0)
            dayCal.set(java.util.Calendar.MINUTE, 0)
            dayCal.set(java.util.Calendar.SECOND, 0)
            dayCal.set(java.util.Calendar.MILLISECOND, 0)
            val dayStart = dayCal.timeInMillis
            val dayEnd = dayStart + 86_400_000L
            val kicks = repo.getKicksSince(dayStart).filter { it.timestamp < dayEnd }
            val avgPerHour = if (kicks.isEmpty()) 0f else kicks.size / 24f
            result.add(Pair(dayNames[dayCal.get(java.util.Calendar.DAY_OF_WEEK) - 1], avgPerHour))
        }
        return result
    }
}

class KickViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return KickViewModel(app) as T
    }
}
