package com.example.languageapp.data.sources.network.responses

import com.example.languageapp.data.models.Word

data class WordPracticeQuestion(
    val answers: List<Word>,
    val question: Word
)