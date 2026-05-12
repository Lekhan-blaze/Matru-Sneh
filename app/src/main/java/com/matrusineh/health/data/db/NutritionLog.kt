package com.matrusineh.health.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nutrition_log")
data class NutritionLog(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val date: String,         // "yyyy-MM-dd"
    val itemIndex: Int,
    val checked: Boolean = false
)
