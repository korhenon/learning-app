package com.example.languageapp.domain.usecase

import com.example.languageapp.data.sources.network.responses.WordPracticeQuestion
import com.example.languageapp.domain.QuestionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetWordPracticeUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) {
    suspend fun invoke(): Result<WordPracticeQuestion> {
        return withContext(Dispatchers.IO) {
            return@withContext questionRepository.getWordPractice()
        }
    }
}