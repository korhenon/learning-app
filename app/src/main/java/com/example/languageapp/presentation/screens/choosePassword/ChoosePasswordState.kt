package com.example.languageapp.presentation.screens.choosePassword

import com.example.languageapp.presentation.utils.InternetState

data class ChoosePasswordState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val isPasswordValid: Boolean = true,
    val confirmPassword: String = "",
    val acceptPrivacy: Boolean = false,
    val internetState: InternetState = InternetState()
)
