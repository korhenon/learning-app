package com.example.languageapp.presentation.screens.choosePassword

data class ChoosePasswordState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val isPasswordValid: Boolean = true,
    val confirmPassword: String = "",
    val acceptPrivacy: Boolean = false
)
