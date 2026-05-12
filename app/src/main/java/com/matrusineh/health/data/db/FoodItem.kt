package com.matrusineh.health.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Static food item master list — seeded into the DB at first install
 * via RoomDatabase.Callback in AppDatabase.
 */
@Entity(tableName = "food_item")
data class FoodItem(
    @PrimaryKey val id: Int,
    val nameEn: String,          // English name e.g. "Ragi"
    val nameKn: String,          // Kannada name e.g. "ರಾಗಿ"
    val emoji: String            // Emoji icon e.g. "🌾"
)
