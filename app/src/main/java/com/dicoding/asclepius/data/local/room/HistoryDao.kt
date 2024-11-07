package com.dicoding.asclepius.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.asclepius.data.local.entity.HistoryEntity

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history")
    suspend fun getAllHistory(): List<HistoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: HistoryEntity)

    @Query("DELETE FROM history")
    suspend fun deleteAllHistory()
}