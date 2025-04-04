package com.example.languageapp.presentation.screens.profile

interface ProfileAction {
    data object SwitchToDark : ProfileAction
    data object ChangeLanguage : ProfileAction
    data object ChangeImage : ProfileAction
    data object Logout : ProfileAction
}