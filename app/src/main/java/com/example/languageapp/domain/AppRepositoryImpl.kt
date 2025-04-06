package com.example.languageapp.domain

import com.example.languageapp.data.sources.local.KeyValueDataRepository
import com.example.languageapp.domain.navigation.Destination

class AppRepositoryImpl(
    private val keyValueRepository: KeyValueDataRepository
) : AppRepository {
    override fun getStartOnboardingPage(): Int {
        val page = keyValueRepository.onboardingPage
        nextOnboarding()
        return page
    }

    override fun nextOnboarding() {
        if (keyValueRepository.onboardingPage < 3) {
            keyValueRepository.setOnboardingPage(keyValueRepository.onboardingPage + 1)
        }
    }

    override fun skipOnboarding() {
        keyValueRepository.setOnboardingPage(3)
    }

    override fun getStartDestination(): Destination {
        return if (keyValueRepository.isUserAuthenticated()) {
            Destination.Home
        } else if (keyValueRepository.onboardingPage > 2) {
            Destination.Login
        } else {
            Destination.Onboarding
        }
    }
}