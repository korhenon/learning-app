package com.example.languageapp.ui.screens.selectLanguage

import com.example.languageapp.data.Languages

data class SelectLanguageState(
    val languages: List<String> = Languages,
    val selectedLanguage: String = Languages[0]
)
