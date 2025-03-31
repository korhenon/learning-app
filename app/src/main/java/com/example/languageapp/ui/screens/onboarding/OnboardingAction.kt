package com.example.languageapp.ui.screens.onboarding

sealed interface OnboardingAction {
    data object Next: OnboardingAction
    data object Skip : OnboardingAction
}