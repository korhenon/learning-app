package com.example.languageapp.presentation.screens.wordPractice

import com.example.languageapp.data.models.Word

sealed interface WordPracticeAction {
    data object Back : WordPracticeAction
    data class SelectWord(val word: Word) : WordPracticeAction
    data object Check : WordPracticeAction
    data object Next : WordPracticeAction
}