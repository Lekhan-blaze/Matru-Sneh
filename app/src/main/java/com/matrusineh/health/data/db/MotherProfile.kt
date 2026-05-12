package com.matrusineh.health.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mother_profile")
data class MotherProfile(
    @PrimaryKey val id: Int = 1,
    val name: String,
    val pregnancyWeekStart: Long   // epoch ms of week-1 start
)
