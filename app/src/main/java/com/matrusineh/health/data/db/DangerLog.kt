package com.matrusineh.health.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "danger_log")
data class DangerLog(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val timestamp: Long = System.currentTimeMillis(),
    val signs: String   // CSV of selected sign indices
)
