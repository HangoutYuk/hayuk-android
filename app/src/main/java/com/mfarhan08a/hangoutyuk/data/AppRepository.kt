package com.mfarhan08a.hangoutyuk.data

import android.location.Location
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
import okhttp3.MultipartBody
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


    fun getId(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[ID]
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
                val response = apiService.getUserbyId(token, id)
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

    fun getAllPlaces(token: String, location: Location): LiveData<Result<PlaceResponse>> = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
            val latlng = "${location.latitude},${location.longitude}"
            val response = apiService.getPlacesRecomendation(token, latlng)
            emit(Result.Success(response))
            Log.d(TAG, response.toString())
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
            emit(Result.Error(e.message.toString()))
        }
    }

    /*fun getDetailPlace(id: Int): LiveData<Result<Place>> = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
            val response = FakeDataPlace.placesData[id - 1]
            Log.d(TAG, response.toString())
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }*/

//    fun getAllPlaces(): LiveData<Result<List<Place>>> =
//        liveData(Dispatchers.IO) {
//            emit(Result.Loading)
//            try {
////                val response = apiService.getPlacesRecomendation(token, location)
//                val response = FakeData.place
//                val dataPlace = ArrayList<Place>()
//
//                for (i in 0 until response.length()) {
//                    val placeDetail = response.getJSONObject(i)
//                    val placeReview = placeDetail.getJSONArray("review")
//                    val placeSchedule = placeDetail.getJSONArray("schedule")
//                    val dataReview = ArrayList<Review>()
//                    val dataSchedule = ArrayList<String>()
//                    val dataSchedules = ArrayList<List<String>>()
//
//                    for (j in 0 until placeReview.length()) {
//                        val rev = placeReview.getJSONObject(j)
//                        dataReview.add(
//                            Review(
//                                author = rev.getString("author"),
//                                rating = rev.getString("rating").toInt(),
//                                id = rev.getString("id"),
//                                text = rev.getString("text"),
//                                time = rev.getString("time"),
//                            )
//                        )
//                    }
//
//                    if (placeSchedule.getString(0) != "null") {
//                        val sch = placeSchedule.getJSONArray(0)
//                        for (k in 0 until sch.length()) {
//                            dataSchedule.add(sch.getString(k))
//                        }
//                    } else {
//                        dataSchedule.add("null")
//                    }
//
//                    dataSchedules.add(dataSchedule)
//                    val place = Place(
//                        id = placeDetail.getString("id"),
//                        website = placeDetail.getString("website"),
//                        address = placeDetail.getString("address"),
//                        latitude = placeDetail.getString("latitude").toDouble(),
//                        rating = placeDetail.getString("rating").toDouble(),
//                        about = placeDetail.getString("about"),
//                        photo = placeDetail.getString("photo"),
//                        phone = placeDetail.getString("phone"),
//                        schedule = dataSchedules,
//                        review = dataReview,
//                        name = placeDetail.getString("name"),
//                        category = placeDetail.getString("category"),
//                        totalReview = placeDetail.getString("totalReview").toInt(),
//                        longitude = placeDetail.getString("longitude").toDouble(),
//                    )
//                    dataPlace.add(place)
//                }
//                emit(Result.Success(dataPlace))
//                Log.d(TAG, dataPlace.toString())
//            } catch (e: Exception) {
//                emit(Result.Error(e.message.toString()))
//                Log.d(TAG, e.toString())
//            }
//        }

    companion object {
        private val TAG = AppRepository::class.java.simpleName

        // datastore
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val ID = stringPreferencesKey("id")

        @Volatile
        private var instance: AppRepository? = null
        fun getInstance(
            apiService: ApiService, dataStore: DataStore<Preferences>
        ): AppRepository = instance ?: synchronized(this) {
            instance ?: AppRepository(apiService, dataStore)
        }.also { instance = it }
    }
}