package com.mfarhan08a.hangoutyuk.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mfarhan08a.hangoutyuk.data.AppRepository
import com.mfarhan08a.hangoutyuk.di.Injection
import com.mfarhan08a.hangoutyuk.ui.detail.DetailViewModel
import com.mfarhan08a.hangoutyuk.ui.home.HomeViewModel
import com.mfarhan08a.hangoutyuk.ui.login.LoginViewModel
import com.mfarhan08a.hangoutyuk.ui.maps.MapsViewModel
import com.mfarhan08a.hangoutyuk.ui.profile.ProfileViewModel
import com.mfarhan08a.hangoutyuk.ui.register.RegisterViewModel

class ViewModelFactory private constructor(
    private val repository: AppRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> return HomeViewModel(repository) as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> return DetailViewModel(repository) as T
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> return RegisterViewModel(repository) as T
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> return LoginViewModel(repository) as T
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> return ProfileViewModel(repository) as T
            modelClass.isAssignableFrom(MapsViewModel::class.java) -> return MapsViewModel(repository) as T
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> return ProfileViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(Injection.provideRepository(context))
        }.also { instance = it }
    }
}