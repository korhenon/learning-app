package com.example.languageapp.presentation.screens.audition

import com.example.languageapp.data.models.Word

data class AuditionState(
    val question: Word = Word("", "", ""),
    val recognitionResults: List<String> = listOf(),
    val isRight: Boolean = false
)
