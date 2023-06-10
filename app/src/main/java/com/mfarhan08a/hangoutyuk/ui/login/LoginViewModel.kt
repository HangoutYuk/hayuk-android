package com.mfarhan08a.hangoutyuk.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mfarhan08a.hangoutyuk.data.AppRepository
import kotlinx.coroutines.Dispatchers

class LoginViewModel(private val repository: AppRepository) : ViewModel() {
    fun login(email: String, password: String) = repository.login(email, password)
    fun getToken() = repository.getToken().asLiveData(Dispatchers.IO)
}