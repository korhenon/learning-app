package com.example.languageapp.data.sources.local

import com.example.languageapp.data.models.SavedSignUpState

class KeyValueDataRepositoryImpl(
    private val service: KeyValueService
) : KeyValueDataRepository {
    override val email
        get() = service.email
    override val onboardingPage: Int
        get() = service.onboardingPage

    override fun setSavedSignUpState(
        firstName: String?,
        secondName: String?,
        email: String?,
        password: String?,
        confirmPassword: String?
    ) {
        if (firstName != null) {
            service.signUpFirstName = firstName
        }
        if (secondName != null) {
            service.signUpSecondName = secondName
        }
        if (email != null) {
            service.signUpEmail = email
        }
        if (password != null) {
            service.signUpPassword = password
        }
        if (confirmPassword != null) {
            service.signUpPasswordConfirm = confirmPassword
        }
    }


    override fun setOnboardingPage(n: Int) {
        service.onboardingPage = n
    }

    override fun getSavedSignUpState(): SavedSignUpState {
        return SavedSignUpState(
            email = service.signUpEmail,
            password = service.signUpPassword,
            confirmPassword = service.signUpPasswordConfirm,
            firstName = service.signUpFirstName,
            secondName = service.signUpSecondName
        )
    }

    override fun clearSavedSignUpState() {
        SavedSignUpState(
            email = "",
            password = "",
            confirmPassword = "",
            firstName = "",
            secondName = ""
        )
    }

    override fun setEmail(email: String) {
        service.email = email
    }

    override fun removeEmail() {
        service.email = null
    }
}