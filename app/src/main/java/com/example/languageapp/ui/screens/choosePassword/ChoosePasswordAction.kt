package com.example.languageapp.ui.screens.choosePassword

sealed interface ChoosePasswordAction {
    data object Back: ChoosePasswordAction
    data object Signup: ChoosePasswordAction
    data object Login: ChoosePasswordAction
    data object OpenPrivacy: ChoosePasswordAction

    data class ChangePrivacy(val value: Boolean): ChoosePasswordAction
    data class ChangePassword(val value: String): ChoosePasswordAction
    data class ChangeConfirmPassword(val value: String): ChoosePasswordAction
}