package com.example.languageapp.ui.screens.onboarding

import com.example.languageapp.data.OnboardingPages
import com.example.languageapp.data.models.OnboardingPage

data class OnboardingState(
    val page: Int = 0,
    val pages: List<OnboardingPage> = OnboardingPages
)
