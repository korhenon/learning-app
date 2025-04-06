package com.example.languageapp.domain

import com.example.languageapp.data.exceptions.DataException
import com.example.languageapp.data.exceptions.ExceptionReason
import com.example.languageapp.data.models.SavedSignUpState
import com.example.languageapp.data.models.User
import com.example.languageapp.data.sources.local.KeyValueDataRepository
import com.example.languageapp.data.sources.network.AuthDataRepository

class AuthRepositoryImpl(
    private val keyValueRepository: KeyValueDataRepository,
    private val authRepository: AuthDataRepository
) : AuthRepository {
    override suspend fun login(email: String, password: String): Throwable? {
        val networkResult = authRepository.login(email, password)
        if (networkResult == null) {
            keyValueRepository.setEmail(email)
        }
        return networkResult
    }

    override suspend fun getCurrentUser(): Result<User> {
        if (keyValueRepository.isUserAuthenticated()) {
            val networkResult = authRepository.getUserInfo(keyValueRepository.email!!)
            return networkResult.map {
                User(
                    name = it.firstName + " " + it.secondName,
                    photo = it.image
                )
            }
        }
        return Result.failure(DataException(ExceptionReason.UserNotAuthenticated))
    }

    override suspend fun signUp(
        firstName: String,
        secondName: String,
        email: String,
        password: String
    ): Throwable? {
        val networkResult = authRepository.signUp(firstName, secondName, email, password)
        if (networkResult == null) {
            keyValueRepository.clearSavedSignUpState()
        }
        return networkResult
    }

    override suspend fun logout() {
        keyValueRepository.removeEmail()
    }

    override fun getSavedSignUpState(): SavedSignUpState {
        return keyValueRepository.getSavedSignUpState()
    }

    override fun updateSavedSignUpState(
        firstName: String?,
        secondName: String?,
        email: String?,
        password: String?,
        confirmPassword: String?
    ) {
        keyValueRepository.setSavedSignUpState(firstName, secondName, email, password, confirmPassword)
    }

}