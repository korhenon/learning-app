package com.example.languageapp.ui.screens.onboarding

import com.example.languageapp.common.OnboardingPages
import com.example.languageapp.data.models.OnboardingPage

data class OnboardingState(
    val page: Int = 0,
    val pages: List<OnboardingPage> = OnboardingPages
)
