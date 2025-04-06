package com.example.languageapp.domain.usecase

import com.example.languageapp.data.models.User
import com.example.languageapp.domain.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun invoke(): Result<User> {
        return withContext(Dispatchers.IO) {
            return@withContext authRepository.getCurrentUser()
        }
    }
}