package com.example.languageapp.domain.usecase

import com.example.languageapp.domain.AppRepository
import com.example.languageapp.domain.navigation.Destination
import javax.inject.Inject

class GetStartDestinationUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    fun invoke(): Destination {
        return appRepository.getStartDestination()
    }
}