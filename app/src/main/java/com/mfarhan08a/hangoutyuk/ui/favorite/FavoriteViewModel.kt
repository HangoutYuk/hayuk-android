package com.mfarhan08a.hangoutyuk.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mfarhan08a.hangoutyuk.data.AppRepository

class FavoriteViewModel(private val repository: AppRepository) : ViewModel() {
    fun getFavoritePlaces() = repository.getFavoritePlaces()
}