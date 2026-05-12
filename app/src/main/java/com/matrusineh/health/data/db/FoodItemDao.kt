package com.matrusineh.health.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FoodItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(items: List<FoodItem>)

    @Query("SELECT * FROM food_item ORDER BY id ASC")
    fun getAllItems(): LiveData<List<FoodItem>>

    @Query("SELECT COUNT(*) FROM food_item")
    suspend fun count(): Int
}
