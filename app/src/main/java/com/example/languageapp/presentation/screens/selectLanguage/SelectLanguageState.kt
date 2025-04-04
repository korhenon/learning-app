package com.example.languageapp.presentation.screens.selectLanguage

import com.example.languageapp.common.Languages

data class SelectLanguageState(
    val languages: List<String> = Languages,
    val selectedLanguage: String = Languages[0]
)
