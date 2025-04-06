package com.example.languageapp.data.sources.local

import com.example.languageapp.data.models.SavedSignUpState

interface KeyValueDataRepository {
    val email: String?

    fun setSavedSignUpState(
        firstName: String?,
        secondName: String?,
        email: String?,
        password: String?,
        confirmPassword: String?
    )

    val onboardingPage: Int
    fun setOnboardingPage(n: Int)

    fun getSavedSignUpState(): SavedSignUpState
    fun clearSavedSignUpState()

    fun setEmail(email: String)

    fun removeEmail()

    fun isUserAuthenticated(): Boolean {
        return email != null
    }
}