package com.mfarhan08a.hangoutyuk.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfarhan08a.hangoutyuk.data.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OnboardingViewModel(private val repository: AppRepository) : ViewModel() {

    fun saveOnboarding(onboard: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveOnboarding(onboard)
        }
    }

}