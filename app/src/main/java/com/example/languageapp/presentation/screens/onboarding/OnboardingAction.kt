package com.example.languageapp.presentation.screens.onboarding

sealed interface OnboardingAction {
    data object Next: OnboardingAction
    data object Skip : OnboardingAction
}