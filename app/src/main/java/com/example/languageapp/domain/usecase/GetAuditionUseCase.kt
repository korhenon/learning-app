package com.example.languageapp.domain.usecase

import com.example.languageapp.data.models.Word
import com.example.languageapp.domain.QuestionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAuditionUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) {
    suspend fun invoke(): Result<Word> {
        return withContext(Dispatchers.IO) {
            return@withContext questionRepository.getAudition()
        }
    }
}