package com.dicoding.asclepius.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long = 0,
    val imagePath: String,
    val result: String,
)