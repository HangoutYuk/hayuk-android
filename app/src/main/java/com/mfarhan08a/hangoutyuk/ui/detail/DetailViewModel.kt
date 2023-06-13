package com.mfarhan08a.hangoutyuk.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mfarhan08a.hangoutyuk.data.AppRepository
import com.mfarhan08a.hangoutyuk.data.model.Place
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: AppRepository) : ViewModel() {
    fun getToken() = repository.getToken().asLiveData(Dispatchers.IO)
    fun getPlaceDetail(token: String, id: String) = repository.getPlaceDetail(token, id)

    fun createPoll(token: String, placeId: String, userId: String) =
        repository.createPoll(token, placeId, userId)

    fun isFavorited(id: String): Boolean = repository.isFavorited(id)

    fun addToFavorite(place: Place) = viewModelScope.launch {
        repository.addToFavorite(place)
    }

    fun deleteFavorite(id: String) = viewModelScope.launch {
        repository.deleteFavorite(id)
    }
}