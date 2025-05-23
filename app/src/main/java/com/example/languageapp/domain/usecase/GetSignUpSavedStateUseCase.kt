package com.example.languageapp.domain.usecase

import com.example.languageapp.data.models.SavedSignUpState
import com.example.languageapp.domain.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetSignUpSavedStateUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun invoke(): SavedSignUpState {
        return withContext(Dispatchers.IO) {
            return@withContext authRepository.getSavedSignUpState()
        }
    }
}