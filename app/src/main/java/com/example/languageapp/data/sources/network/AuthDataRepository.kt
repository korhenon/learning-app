package com.example.languageapp.data.sources.network

import com.example.languageapp.data.models.User
import com.example.languageapp.data.sources.network.requests.LoginRequest
import com.example.languageapp.data.sources.network.responses.UserInfo
import kotlinx.coroutines.flow.Flow

interface AuthDataRepository {
    suspend fun login(email: String, password: String): Throwable?
    suspend fun getUserInfo(email: String): Result<UserInfo>
    suspend fun signUp(
        firstName: String,
        secondName: String,
        email: String,
        password: String
    ): Throwable?
}