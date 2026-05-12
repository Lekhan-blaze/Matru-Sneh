package com.matrusineh.health.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.matrusineh.health.data.db.AppDatabase
import com.matrusineh.health.data.db.FoodItem
import com.matrusineh.health.data.db.NutritionLog
import com.matrusineh.health.data.repository.FoodItemRepository
import com.matrusineh.health.data.repository.NutritionRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class NutritionViewModel(app: Application) : AndroidViewModel(app) {

    private val db = AppDatabase.getInstance(app)
    private val repo = NutritionRepository(db.nutritionDao())
    private val foodRepo = FoodItemRepository(db.foodItemDao())

    private val todayStr: String get() = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())

    /** All food items seeded from DB — drives the dynamic checklist in the UI */
    val foodItems: LiveData<List<FoodItem>> = foodRepo.getAllItems()

    val todayLogs: LiveData<List<NutritionLog>> = repo.getForDate(todayStr)

    private val _checkedCount = MutableLiveData(0)
    val checkedCount: LiveData<Int> = _checkedCount

    fun setItemChecked(itemIndex: Int, checked: Boolean) {
        viewModelScope.launch {
            repo.setItemChecked(todayStr, itemIndex, checked)
        }
    }

    fun updateCheckedCount(logs: List<NutritionLog>) {
        _checkedCount.value = logs.count { it.checked }
    }
}

class NutritionViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return NutritionViewModel(app) as T
    }
}
