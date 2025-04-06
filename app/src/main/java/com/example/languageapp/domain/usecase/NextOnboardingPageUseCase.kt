package com.example.languageapp.domain.usecase

import com.example.languageapp.domain.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NextOnboardingPageUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    suspend fun invoke() {
        withContext(Dispatchers.IO) {
            appRepository.nextOnboarding()
        }
    }
}