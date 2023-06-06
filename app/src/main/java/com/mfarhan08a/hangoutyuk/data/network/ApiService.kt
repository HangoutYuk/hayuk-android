package com.mfarhan08a.hangoutyuk.data.network

import com.mfarhan08a.hangoutyuk.data.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*


interface ApiService {

//    @FormUrlEncoded
//    @POST("register")
//    suspend fun register(
//        @Field("name") name: String,
//        @Field("email") email: String,
//        @Field("password") password: String
//    ): RegisterResponse

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

    @GET("ml-endpoint/{location}")
    suspend fun getPlacesRecomendation(
        @Header("auth-token") token: String,
        @Path("location") location: String,
    ): PlaceResponse

    @GET("user/{id}")
    suspend fun getUserbyId(
        @Header("auth-token") token: String,
        @Path("id") id: String,
    ): UserResponse

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
}