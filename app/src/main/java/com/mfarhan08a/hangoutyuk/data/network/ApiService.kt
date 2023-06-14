package com.mfarhan08a.hangoutyuk.data.network

import com.mfarhan08a.hangoutyuk.data.model.*
import okhttp3.MultipartBody
import retrofit2.http.*


interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("register")
    suspend fun register(
        @Body body: String?
    ): RegisterResponse

    @Headers("Content-Type: application/json")
    @POST("login")
    suspend fun login(
        @Body body: String?
    ): LoginResponse

    @Headers("Content-Type: application/json")
    @DELETE("user/{id}")
    suspend fun deleteUser(
        @Header("auth-token") token: String,
        @Path("id") id: String,
    ): UpdateResponse

    @GET("places/{location}")
    suspend fun getPlacesRecommendation(
        @Header("auth-token") token: String,
        @Path("location") location: String,
    ): PlaceResponse

    @GET("places/details/{placeId}")
    suspend fun getPlaceDetail(
        @Header("auth-token") token: String,
        @Path("placeId") placeId: String,
    ): PlaceDetailResponse

    @GET("user/{id}")
    suspend fun getUserById(
        @Header("auth-token") token: String,
        @Path("id") id: String,
    ): UserResponse


    @GET("user/{id}/polls")
    suspend fun getPollsUser(
        @Header("auth-token") token: String,
        @Path("id") id: String,
    ): PollResponse

    @Headers("Content-Type: application/json")
    @HTTP(method = "DELETE", path = "user/{id}/polls", hasBody = true)
    suspend fun deletePoll(
        @Header("auth-token") token: String,
        @Path("id") id: String,
        @Body request: DeletePollRequest
        ): UpdateResponse

    @Multipart
    @POST("user/{id}")
    suspend fun postPhotoProfile(
        @Header("auth-token") token: String,
        @Path("id") id: String,
        @Part photoFile: MultipartBody.Part,
    ): PhotoResponse

    @Headers("Content-Type: application/json")
    @PATCH("user/{id}")
    suspend fun updateProfile(
        @Header("auth-token") token: String,
        @Path("id") id: String,
        @Body body: String?,
    ) : UpdateResponse

    @Headers("Content-Type: application/json")
    @POST("poll/create")
    suspend fun createPoll(
        @Header("auth-token") token: String,
        @Body body: String?
    ): CreatePollResponse
}

