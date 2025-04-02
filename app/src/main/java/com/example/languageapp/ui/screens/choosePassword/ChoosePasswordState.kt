package com.example.languageapp.ui.screens.choosePassword

import com.example.languageapp.ui.screens.signup.SignUpAction

data class ChoosePasswordState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val isPasswordValid: Boolean = true,
    val confirmPassword: String = "",
    val acceptPrivacy: Boolean = false
)
