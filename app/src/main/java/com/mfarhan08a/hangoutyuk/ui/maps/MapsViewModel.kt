package com.mfarhan08a.hangoutyuk.ui.maps

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mfarhan08a.hangoutyuk.data.AppRepository
import kotlinx.coroutines.Dispatchers

class MapsViewModel(private val repository: AppRepository) : ViewModel() {
    fun getToken() = repository.getToken().asLiveData(Dispatchers.IO)
    fun getPlaceRecommendation(token: String, location: Location) =
        repository.getPlaceRecommendation(token, location)

    fun getFavoritePlaces() = repository.getFavoritePlaces()
}