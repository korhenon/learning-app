package com.example.languageapp.ui.screens.selectLanguage

sealed interface SelectLanguageAction {
    data class ChangeLanguage(val selectedLanguage: String): SelectLanguageAction
}