package com.example.languageapp.data.sources.network

import com.example.languageapp.data.sources.network.responses.UserTopInfo
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface UserService {
    @GET("user-top")
    suspend fun userTop(): List<UserTopInfo>

    @POST("upload/{email}")
    suspend fun uploadAvatar(@Path("email") email: String, @Part image: MultipartBody.Part)
}