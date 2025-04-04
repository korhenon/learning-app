package com.example.languageapp.presentation.screens.selectLanguage

sealed interface SelectLanguageAction {
    data class ChangeLanguage(val selectedLanguage: String): SelectLanguageAction
    data object Choose: SelectLanguageAction
}