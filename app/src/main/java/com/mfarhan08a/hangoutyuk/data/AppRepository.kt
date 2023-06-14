package com.mfarhan08a.hangoutyuk.data

import android.location.Location
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.mfarhan08a.hangoutyuk.data.local.entity.FavoriteEntity
import com.mfarhan08a.hangoutyuk.data.local.room.FavoriteDao
import com.mfarhan08a.hangoutyuk.data.model.*
import com.mfarhan08a.hangoutyuk.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.MultipartBody
import org.json.JSONObject
import retrofit2.HttpException

class AppRepository(
    private val apiService: ApiService,
    private val dataStore: DataStore<Preferences>,
    private val favoriteDao: FavoriteDao,
) {
    fun getToken(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }
    }

    fun getOnboarding(): Flow<Boolean?> {
        return dataStore.data.map { preferences ->
            preferences[ONBOARDING] ?: false
        }
    }

    fun getId(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[ID]
        }
    }

    suspend fun saveOnboarding(onboard: Boolean) {
        dataStore.edit { preferences ->
            preferences[ONBOARDING] = onboard
        }
    }

    private suspend fun saveId(id: String) {
        dataStore.edit { preferences ->
            preferences[ID] = id
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

    fun login(email: String, password: String): LiveData<Result<LoginResponse>> =
        liveData(Dispatchers.IO) {
            emit(Result.Loading)
            val paramObject = JSONObject()
            paramObject.put("email", email)
            paramObject.put("password", password)

            Log.d(TAG, paramObject.toString())
            try {
                val response = apiService.login(paramObject.toString())
                saveToken(response.data.token)
                saveId(response.data.id)
                emit(Result.Success(response))
                Log.d(TAG, response.toString())
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
                Log.d(TAG, e.toString())
            }
        }

    fun register(
        name: String, email: String, password: String
    ): LiveData<Result<RegisterResponse>> = liveData(Dispatchers.IO) {
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

    fun deleteUserById(token: String, id: String): LiveData<Result<UpdateResponse>> =
        liveData(Dispatchers.IO) {
            emit(Result.Loading)
            try {
                val response = apiService.deleteUser(token, id)
                emit(Result.Success(response))
                Log.d(TAG, response.toString())
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
                Log.d(TAG, e.toString())
            }
        }

    fun getUserById(token: String, id: String): LiveData<Result<UserResponse>> =
        liveData(Dispatchers.IO) {
            emit(Result.Loading)
            try {
                val response = apiService.getUserById(token, id)
                emit(Result.Success(response))
                Log.d(TAG, response.toString())
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
                Log.d(TAG, e.toString())
            }
        }

    fun postPhotoProfile(
        token: String,
        id: String,
        file: MultipartBody.Part,
    ): LiveData<Result<PhotoResponse>> = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        Log.d(TAG, "token: $token, id: $id, file: $file")
        try {
            val response = apiService.postPhotoProfile(token, id, file)
            Log.d(TAG, response.toString())
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
            Log.d(TAG, e.toString())
        }
    }

    fun updateProfile(
        token: String,
        id: String,
        name: String? = null,
        email: String? = null,
        password: String? = null
    ): LiveData<Result<UpdateResponse>> = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        Log.d(TAG, "param $name, $email, $password")
        try {
            val paramObject = JSONObject()
            if (name != null) {
                paramObject.put("name", name)
            }
            if (email != null) {
                paramObject.put("email", email)
            }
            if (!password.isNullOrEmpty()) {
                paramObject.put("password", password)
            }

            Log.d(TAG, paramObject.toString())
            val response = apiService.updateProfile(token, id, paramObject.toString())
            emit(Result.Success(response))
            Log.d(TAG, response.toString())
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getPlaceRecommendation(token: String, location: Location): LiveData<Result<PlaceResponse>> =
        liveData(Dispatchers.IO) {
            emit(Result.Loading)
            try {
                val latlng = "${location.latitude},${location.longitude}"
                val response = apiService.getPlacesRecommendation(token, latlng)
                emit(Result.Success(response))
                Log.d(TAG, response.toString())
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                emit(Result.Error(e.message.toString()))
            }
        }

    fun getPlaceDetail(token: String, id: String): LiveData<Result<PlaceDetailResponse>> =
        liveData(Dispatchers.IO) {
            emit(Result.Loading)
            try {
                val response = apiService.getPlaceDetail(token, id)
                emit(Result.Success(response))
                Log.d(TAG, response.toString())
            } catch (e: HttpException) {
                Log.d(TAG, e.toString())
                emit(Result.Error(e.code().toString()))
            }
        }

    fun createPoll(
        token: String, placeId: String, userId: String
    ): LiveData<Result<CreatePollResponse>> = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
            val paramObject = JSONObject()
            paramObject.put("placeId", placeId)
            paramObject.put("userId", userId)
            Log.d(TAG, paramObject.toString())

            val response = apiService.createPoll(token, paramObject.toString())
            emit(Result.Success(response))
            Log.d(TAG, response.toString())
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
            emit(Result.Error(e.toString()))
        }
    }

    fun getPollsUser(
        token: String,
        userId: String,
    ): LiveData<Result<PollResponse>> = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
            val response = apiService.getPollsUser(token, userId)
            emit(Result.Success(response))
            Log.d(TAG, response.toString())
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
            Log.d(TAG, e.toString())
        }
    }

    fun deletePoll(
        token: String,
        userId: String,
        pollId: String,
    ): LiveData<Result<UpdateResponse>> = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
            val paramObject = JSONObject()
            paramObject.put("pollId", pollId)
            val poll = DeletePollRequest(pollId)

            val response = apiService.deletePoll(token, userId, poll)
            emit(Result.Success(response))
            Log.d(TAG, response.toString())
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
            Log.d(TAG, e.toString())
        }
    }

    suspend fun addToFavorite(place: Place) {
        val favoritePlace = FavoriteEntity(
            id = place.id!!,
            photo = place.photo!!,
            name = place.name!!,
            category = place.category!!,
            rating = place.rating!!,
            totalReview = place.totalReview!!,
            latitude = place.latitude!!,
            longitude = place.longitude!!,
        )
        favoriteDao.insertFavorite(favoritePlace)
    }

    fun isFavorited(id: String): Boolean = favoriteDao.isFavorited(id)

    suspend fun deleteFavorite(id: String) {
        favoriteDao.deleteFavorite(id)
    }

    fun getFavoritePlaces(): LiveData<List<FavoriteEntity>> {
        return favoriteDao.getFavoritePlaces()
    }

    companion object {
        private val TAG = AppRepository::class.java.simpleName

        // datastore
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val ID = stringPreferencesKey("id")
        private val ONBOARDING = booleanPreferencesKey("onboarding")

        @Volatile
        private var instance: AppRepository? = null
        fun getInstance(
            apiService: ApiService,
            dataStore: DataStore<Preferences>,
            favoriteDao: FavoriteDao,
        ): AppRepository = instance ?: synchronized(this) {
            instance ?: AppRepository(apiService, dataStore, favoriteDao)
        }.also { instance = it }
    }
}