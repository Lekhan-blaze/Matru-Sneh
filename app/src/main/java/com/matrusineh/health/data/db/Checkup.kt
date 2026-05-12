package com.matrusineh.health.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "checkups")
data class Checkup(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val type: String,
    val dateEpoch: Long,
    val notified: Boolean = false
)
