package com.example.languageapp.data.sources.network

import com.example.languageapp.data.sources.network.requests.LoginRequest
import com.example.languageapp.data.sources.network.requests.RegisterRequest
import com.example.languageapp.data.sources.network.responses.UserInfo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {
    @POST("login")
    suspend fun login(@Body body: LoginRequest)

    @POST("register")
    suspend fun register(@Body body: RegisterRequest)

    @GET("user/{email}")
    suspend fun getUserInfo(@Path("email") email: String): UserInfo
}