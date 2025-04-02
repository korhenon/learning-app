package com.example.languageapp.ui.screens.signup

data class SignUpState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val isEmailValid: Boolean = true,
)
