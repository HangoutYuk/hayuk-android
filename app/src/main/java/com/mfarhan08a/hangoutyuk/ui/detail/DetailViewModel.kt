package com.mfarhan08a.hangoutyuk.ui.detail

import androidx.lifecycle.ViewModel
import com.mfarhan08a.hangoutyuk.data.AppRepository

class DetailViewModel(private val repository: AppRepository) : ViewModel() {

    fun getDetailPlace(id: Int) = repository.getDetailPlace(id)

}