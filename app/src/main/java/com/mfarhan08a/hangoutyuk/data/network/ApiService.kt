package com.mfarhan08a.hangoutyuk.data.network

import com.mfarhan08a.hangoutyuk.data.model.LoginResponse
import com.mfarhan08a.hangoutyuk.data.model.PlaceResponse
import com.mfarhan08a.hangoutyuk.data.model.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

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

    @GET("ml-endpoint/{location}")
    suspend fun getPlacesRecomendation(
        @Header("auth-token") token: String,
        @Path("location") id: String,
    ): PlaceResponse

    @GET("user/{id}")
    suspend fun getUserbyId(
        @Header("auth-token") token: String,
        @Path("id") id: String,
    ): RegisterResponse


}