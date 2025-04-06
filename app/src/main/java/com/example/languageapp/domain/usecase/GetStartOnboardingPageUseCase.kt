package com.example.languageapp.domain.usecase

import com.example.languageapp.domain.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetStartOnboardingPageUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    suspend fun invoke(): Int {
        return withContext(Dispatchers.IO) {
            return@withContext appRepository.getStartOnboardingPage()
        }
    }
}