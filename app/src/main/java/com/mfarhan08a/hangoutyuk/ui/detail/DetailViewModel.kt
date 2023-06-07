package com.mfarhan08a.hangoutyuk.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mfarhan08a.hangoutyuk.data.AppRepository
import kotlinx.coroutines.Dispatchers

class DetailViewModel(private val repository: AppRepository) : ViewModel() {
    fun getToken() = repository.getToken().asLiveData(Dispatchers.IO)
    fun getPlaceDetail(token: String, id: String) = repository.getPlaceDetail(token, id)

}