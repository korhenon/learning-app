package com.example.languageapp.presentation.screens.audition

import com.example.languageapp.data.models.Word
import com.example.languageapp.presentation.utils.InternetState

data class AuditionState(
    val question: Word = Word("", "", ""),
    val recognitionResults: List<String> = listOf(),
    val isRight: Boolean = false,
    val internetState: InternetState = InternetState()
)
