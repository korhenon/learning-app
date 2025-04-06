package com.example.languageapp.domain.usecase

import com.example.languageapp.domain.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend fun invoke(
        firstName: String,
        secondName: String,
        email: String,
        password: String
    ): Throwable? {
        return withContext(Dispatchers.IO) {
            return@withContext authRepository.signUp(firstName, secondName, email, password)
        }
    }
}