package com.example.languageapp.presentation.screens.wordPractice

import com.example.languageapp.data.models.Word

data class WordPracticeState(
    val question: Word = Word("", "", ""),
    val answers: List<Word> = listOf(),
    val selected: Word = Word("", "", ""),
    val isChecked: Boolean = false
)
