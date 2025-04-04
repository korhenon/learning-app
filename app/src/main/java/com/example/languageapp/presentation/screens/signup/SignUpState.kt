package com.example.languageapp.presentation.screens.signup

data class SignUpState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val isEmailValid: Boolean = true,
)
