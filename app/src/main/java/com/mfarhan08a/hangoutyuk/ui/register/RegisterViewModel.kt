package com.mfarhan08a.hangoutyuk.ui.register

import androidx.lifecycle.ViewModel
import com.mfarhan08a.hangoutyuk.data.AppRepository

class RegisterViewModel(private val repository: AppRepository) : ViewModel() {
    fun register(name: String, email: String, password: String) =
        repository.register(name, email, password)
}