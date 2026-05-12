package com.matrusineh.health.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kicks")
data class Kick(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val timestamp: Long = System.currentTimeMillis()
)
