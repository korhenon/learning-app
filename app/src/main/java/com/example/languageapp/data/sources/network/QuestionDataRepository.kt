package com.example.languageapp.data.sources.network

import com.example.languageapp.data.models.GuessTheAnimalQuestion
import com.example.languageapp.data.models.Word
import com.example.languageapp.data.sources.network.responses.WordPracticeQuestion
import com.example.languageapp.domain.navigation.Destination

interface QuestionDataRepository {
    suspend fun getGuessTheAnimal(email: String): Result<GuessTheAnimalQuestion>
    suspend fun getWordPractice(email: String): Result<WordPracticeQuestion>
    suspend fun getAudition(email: String): Result<Word>
}