package com.mfarhan08a.hangoutyuk.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.mfarhan08a.hangoutyuk.data.model.*
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

    fun getName(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[NAME]
        }
    }

    fun getId(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[ID]
        }
    }

    private suspend fun saveNameEmail(name: String, email: String, id: String) {
        dataStore.edit { preferences ->
            preferences[NAME] = name
            preferences[EMAIL] = email
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
            try {
                val paramObject = JSONObject()
                paramObject.put("email", email)
                paramObject.put("password", password)

                val response = apiService.login(paramObject.toString())
                saveToken(response.data.token)
                saveNameEmail(response.data.name, response.data.email, response.data.id)
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

    fun getUserById(token: String, id: String): LiveData<Result<RegisterResponse>> =
        liveData(Dispatchers.IO) {
            emit(Result.Loading)
            try {
                val response = apiService.getUserbyId(token, id)
                emit(Result.Success(response))
                Log.d(TAG, response.toString())
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
                Log.d(TAG, e.toString())
            }
        }
/*
    fun getAllPlaces(): LiveData<Result<List<Place>>> = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
            val response = FakeDataPlace.placesData
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getDetailPlace(id: Int): LiveData<Result<Place>> = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
            val response = FakeDataPlace.placesData[id - 1]
            Log.d(TAG, response.toString())
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }
*/

    fun getAllPlaces(): LiveData<Result<List<Place>>> =
        liveData(Dispatchers.IO) {
            emit(Result.Loading)
            try {
//                val response = apiService.getPlacesRecomendation(token, location)
                val response = FakeData.place
                var dataPlace = ArrayList<Place>()

                for (i in 0 until response.length()) {
                    val placeDetail = response.getJSONObject(i)
                    val placeReview = placeDetail.getJSONArray("review")
                    var dataReview = ArrayList<Review>()

                    for (j in 0 until placeReview.length()) {
                        val rev = placeReview.getJSONObject(j)
                        dataReview.add(
                            Review(
                                author = rev.getString("author"),
                                rating = rev.getString("rating").toDouble(),
                                id = rev.getString("id"),
                                text = rev.getString("text"),
                                time = rev.getString("time"),
                            )
                        )
                    }
                    var place = Place(
                        id = placeDetail.getString("id"),
                        website = placeDetail.getString("website"),
                        address = placeDetail.getString("address"),
                        latitude = placeDetail.getString("latitude").toDouble(),
                        rating = placeDetail.getString("rating").toDouble(),
                        about = placeDetail.getString("about"),
                        photo = placeDetail.getString("photo"),
                        phone = placeDetail.getString("phone"),
                        review = dataReview,
                        name = placeDetail.getString("name"),
                        category = placeDetail.getString("category"),
                        totalReview = placeDetail.getString("totalReview").toInt(),
                        longitude = placeDetail.getString("longitude").toDouble(),
                    )
                    dataPlace.add(place)
                }
                emit(Result.Success(dataPlace))
                Log.d(TAG, response.toString())
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
                Log.d(TAG, e.toString())
            }
        }

//    fun getDetailPlace(id: Int): LiveData<Result<Place>> = liveData(Dispatchers.IO) {
//        emit(Result.Loading)
//        try {
//            val response = FakeDataPlace.placesData[id - 1]
//            Log.d(TAG, response.toString())
//            emit(Result.Success(response))
//        } catch (e: Exception) {
//            emit(Result.Error(e.message.toString()))
//        }
//    }

    companion object {
        private val TAG = AppRepository::class.java.simpleName

        private val TOKEN_KEY = stringPreferencesKey("token")
        private val NAME = stringPreferencesKey("name")
        private val EMAIL = stringPreferencesKey("email")
        private val ID = stringPreferencesKey("id")
//        private val LOCALE_KEY = stringPreferencesKey("locale")

        @Volatile
        private var instance: AppRepository? = null
        fun getInstance(
            apiService: ApiService, dataStore: DataStore<Preferences>
        ): AppRepository = instance ?: synchronized(this) {
            instance ?: AppRepository(apiService, dataStore)
        }.also { instance = it }
    }
}