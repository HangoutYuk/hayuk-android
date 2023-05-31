package com.mfarhan08a.hangoutyuk.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mfarhan08a.hangoutyuk.data.AppRepository
import kotlinx.coroutines.Dispatchers

class ProfileViewModel(private val repository: AppRepository) : ViewModel() {

    fun getUserById(token: String, id: String) = repository.getUserById(token, id)

    fun getToken() = repository.getToken().asLiveData(Dispatchers.IO)

    fun getId() = repository.getId().asLiveData((Dispatchers.IO))
}