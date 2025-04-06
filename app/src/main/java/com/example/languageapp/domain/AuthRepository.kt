package com.example.languageapp.domain

import com.example.languageapp.data.models.SavedSignUpState
import com.example.languageapp.data.models.User

interface AuthRepository {
    suspend fun login(email: String, password: String): Throwable?

    suspend fun getCurrentUser(): Result<User>

    suspend fun signUp(
        firstName: String,
        secondName: String,
        email: String,
        password: String
    ): Throwable?

    suspend fun logout()

    fun getSavedSignUpState(): SavedSignUpState

    fun updateSavedSignUpState(
        firstName: String?,
        secondName: String?,
        email: String?,
        password: String?,
        confirmPassword: String?
    )
}