package com.example.languageapp.domain.usecase

import com.example.languageapp.domain.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend fun invoke(email: String, password: String): Throwable? {
        return withContext(Dispatchers.IO) {
            return@withContext repository.login(email, password)
        }
    }
}