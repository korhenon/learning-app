package com.example.languageapp.presentation.screens.login

import com.example.languageapp.presentation.utils.InternetState

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isEmailValid: Boolean = true,
    val internetState: InternetState = InternetState()
)
