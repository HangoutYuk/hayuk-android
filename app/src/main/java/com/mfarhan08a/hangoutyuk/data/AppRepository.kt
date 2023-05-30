package com.mfarhan08a.hangoutyuk.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.mfarhan08a.hangoutyuk.data.model.FakeDataPlace
import com.mfarhan08a.hangoutyuk.data.model.LoginResponse
import com.mfarhan08a.hangoutyuk.data.model.Place
import com.mfarhan08a.hangoutyuk.data.model.RegisterResponse
import com.mfarhan08a.hangoutyuk.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.json.JSONObject

class AppRepository(
    private val apiService: ApiService,
    private val dataStore: DataStore<Preferences>,
) {
    fun getToken(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }
    }

    private suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }

//    fun register(
//        name: String,
//        email: String,
//        password: String
//    ): LiveData<Result<RegisterResponse>> =
//        liveData(Dispatchers.IO) {
//            emit(Result.Loading)
//            try {
//                val response = apiService.register(name, email, password)
//                emit(Result.Success(response))
//                Log.d(TAG, response.toString())
//            } catch (e: Exception) {
//                Log.d(TAG, e.toString())
//                emit(Result.Error(e.message.toString()))
//            }
//        }

    fun login(email: String, password: String): LiveData<Result<LoginResponse>> =
        liveData(Dispatchers.IO) {
            emit(Result.Loading)
            try {
                val paramObject = JSONObject()
                paramObject.put("email", email)
                paramObject.put("password", password)

                val response = apiService.login(paramObject.toString())
                saveToken(response.data.token)
                emit(Result.Success(response))
                Log.d(TAG, response.toString())
                Log.d(TAG, "token check: " + getToken())
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
                Log.d(TAG, e.toString())
            }
        }

    fun register(
        name: String,
        email: String,
        password: String
    ): LiveData<Result<RegisterResponse>> =
        liveData(Dispatchers.IO) {
            emit(Result.Loading)
            try {
                val paramObject = JSONObject()
                paramObject.put("name", name)
                paramObject.put("email", email)
                paramObject.put("password", password)

                val response = apiService.register(paramObject.toString())
                emit(Result.Success(response))
                Log.d(TAG, response.toString())
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                emit(Result.Error(e.message.toString()))
            }
        }

    fun getAllPlaces(): LiveData<Result<List<Place>>> = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
            val response = FakeDataPlace.placesData
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getDetailPlace(id: Int): LiveData<Result<Place>> =
        liveData(Dispatchers.IO) {
            emit(Result.Loading)
            try {
                val response = FakeDataPlace.placesData[id - 1]
                Log.d(TAG, response.toString())
                emit(Result.Success(response))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }

    companion object {
        private val TAG = AppRepository::class.java.simpleName

        private val TOKEN_KEY = stringPreferencesKey("token")
//        private val LOCALE_KEY = stringPreferencesKey("locale")

        @Volatile
        private var instance: AppRepository? = null
        fun getInstance(
            apiService: ApiService,
            dataStore: DataStore<Preferences>
        ): AppRepository = instance ?: synchronized(this) {
            instance ?: AppRepository(apiService, dataStore)
        }.also { instance = it }
    }
}