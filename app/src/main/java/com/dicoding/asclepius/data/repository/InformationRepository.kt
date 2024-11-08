package com.dicoding.asclepius.data.repository

import com.dicoding.asclepius.data.local.entity.HistoryEntity
import com.dicoding.asclepius.data.local.room.HistoryDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InformationRepository(
    private val historyDao: HistoryDao
) {
    fun getAllHistory(): Flow<List<HistoryEntity>> = flow {
        emit(historyDao.getAllHistory())
    }

    suspend fun insertHistory(history: HistoryEntity) {
        historyDao.insertHistory(history)
    }

    suspend fun deleteHistory(history: HistoryEntity) {
        historyDao.deleteHistory(history) // Implement delete logic here
    }
}