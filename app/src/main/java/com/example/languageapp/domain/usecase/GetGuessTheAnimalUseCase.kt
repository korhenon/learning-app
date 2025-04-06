package com.example.languageapp.domain.usecase

import com.example.languageapp.data.models.GuessTheAnimalQuestion
import com.example.languageapp.domain.QuestionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetGuessTheAnimalUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) {
    suspend fun invoke(): Result<GuessTheAnimalQuestion> {
        return withContext(Dispatchers.IO) {
            return@withContext questionRepository.getGuessTheAnimal()
        }
    }
}