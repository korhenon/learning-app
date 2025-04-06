package com.example.languageapp.domain

import com.example.languageapp.domain.navigation.Destination

interface AppRepository {
    fun getStartOnboardingPage(): Int
    fun nextOnboarding()
    fun skipOnboarding()

    fun getStartDestination(): Destination
}