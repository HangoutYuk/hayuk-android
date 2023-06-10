package com.mfarhan08a.hangoutyuk.ui.home

import android.location.Location
import androidx.lifecycle.*
import com.mfarhan08a.hangoutyuk.data.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: AppRepository) : ViewModel() {
    fun getToken() = repository.getToken().asLiveData(Dispatchers.IO)
    fun getId() = repository.getId().asLiveData(Dispatchers.IO)
    fun clearToken() {
        viewModelScope.launch {
            repository.clearToken()
        }
    }

    fun getUserById(token: String, id: String) = repository.getUserById(token, id)
    fun getPlaceRecommendation(token: String, location: Location) =
        repository.getPlaceRecommendation(token, location)
}