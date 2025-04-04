package com.example.languageapp.presentation.screens.signup

sealed interface SignUpAction {
    data object Login: SignUpAction
    data object Continue: SignUpAction
    data object Back: SignUpAction

    data class ChangeFirstName(val value: String): SignUpAction
    data class ChangeLastName(val value: String): SignUpAction
    data class ChangeEmail(val value: String): SignUpAction
}