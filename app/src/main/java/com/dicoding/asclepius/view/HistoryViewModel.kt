package com.dicoding.asclepius.view

import androidx.activity.result.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.asclepius.data.local.entity.HistoryEntity
import com.dicoding.asclepius.data.repository.InformationRepository
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: InformationRepository) : ViewModel() {
    val history: LiveData<List<HistoryEntity>> = repository.getAllHistory().asLiveData()

    fun insertHistory(history: HistoryEntity) {
        viewModelScope.launch {
            repository.insertHistory(history)
        }
    }

    fun deleteHistory(history: HistoryEntity) {
        viewModelScope.launch {
            repository.deleteHistory(history)
        }
    }
}