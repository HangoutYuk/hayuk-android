package com.mfarhan08a.hangoutyuk.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mfarhan08a.hangoutyuk.data.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class ProfileViewModel(private val repository: AppRepository) : ViewModel() {
    fun getToken() = repository.getToken().asLiveData(Dispatchers.IO)

    fun getId() = repository.getId().asLiveData((Dispatchers.IO))

    fun clearToken() {
        viewModelScope.launch {
            repository.clearToken()
        }
    }

    fun postPhotoProfile(token: String, id: String, file: MultipartBody.Part) =
        repository.postPhotoProfile(token, id, file)

    fun deleteUser(token: String, id: String) = repository.deleteUserById(token, id)

    fun updateProfile(
        token: String,
        id: String,
        name: String? = null,
        email: String? = null,
        password: String? = null,
    ) = repository.updateProfile(token, id, name, email, password)
}