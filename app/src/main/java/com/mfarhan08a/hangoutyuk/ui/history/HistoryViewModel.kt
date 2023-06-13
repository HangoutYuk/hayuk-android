package com.mfarhan08a.hangoutyuk.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mfarhan08a.hangoutyuk.data.AppRepository
import kotlinx.coroutines.Dispatchers

class HistoryViewModel(private val repository: AppRepository) : ViewModel() {
    fun getToken() = repository.getToken().asLiveData(Dispatchers.IO)
    fun getId() = repository.getId().asLiveData(Dispatchers.IO)
    fun getPollsUser(token: String, id: String) = repository.getPollsUser(token, id)
}