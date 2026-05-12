package com.matrusineh.health.data.repository

import androidx.lifecycle.LiveData
import com.matrusineh.health.data.db.FoodItem
import com.matrusineh.health.data.db.FoodItemDao

class FoodItemRepository(private val foodItemDao: FoodItemDao) {

    /** All food items from DB — seeded at install via DB callback */
    fun getAllItems(): LiveData<List<FoodItem>> = foodItemDao.getAllItems()
}
