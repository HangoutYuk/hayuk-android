package com.mfarhan08a.hangoutyuk.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.mfarhan08a.hangoutyuk.data.AppRepository
import com.mfarhan08a.hangoutyuk.data.network.ApiConfig

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("preferences")

object Injection {
    fun provideRepository(context: Context): AppRepository {
        val apiService = ApiConfig.getApiService()
//        val storyDatabase = StoryDatabase.getDatabase(context)
        return AppRepository.getInstance(apiService, context.dataStore)
    }
}