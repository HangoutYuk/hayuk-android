package com.mfarhan08a.hangoutyuk.ui.home

import androidx.lifecycle.*
import com.mfarhan08a.hangoutyuk.data.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: AppRepository) : ViewModel() {
    fun getToken() = repository.getToken().asLiveData(Dispatchers.IO)
    fun getName() = repository.getName().asLiveData(Dispatchers.IO)

    fun clearToken() {
        viewModelScope.launch {
            repository.clearToken()
        }
    }

//    fun getAllPlaces(token: String, location: String) = repository.getAllPlaces(token, location)
    fun getAllPlaces() = repository.getAllPlaces()

}