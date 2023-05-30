package com.mfarhan08a.hangoutyuk.data.network

import com.mfarhan08a.hangoutyuk.data.model.LoginResponse
import com.mfarhan08a.hangoutyuk.data.model.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

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

}