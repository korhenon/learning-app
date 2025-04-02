package com.example.languageapp.ui.screens.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isEmailValid: Boolean = true,
    val isPasswordValid: Boolean = true
)
