package com.example.languageapp.domain

import com.example.languageapp.data.models.GuessTheAnimalQuestion
import com.example.languageapp.data.models.Word
import com.example.languageapp.data.sources.network.responses.WordPracticeQuestion

interface QuestionRepository {
    suspend fun getGuessTheAnimal(): Result<GuessTheAnimalQuestion>
    suspend fun getWordPractice(): Result<WordPracticeQuestion>
    suspend fun getAudition(): Result<Word>
}