package com.example.languageapp.ui.screens.login

sealed interface LoginAction {
    data object Login : LoginAction
    data object SignUp : LoginAction
    data object Back : LoginAction
    data class ChangeEmail(val value: String) : LoginAction
    data class ChangePassword(val value: String) : LoginAction
}