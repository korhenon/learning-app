package com.example.languageapp.domain

import com.example.languageapp.data.exceptions.DataException
import com.example.languageapp.data.exceptions.ExceptionReason
import com.example.languageapp.data.models.GuessTheAnimalQuestion
import com.example.languageapp.data.models.Word
import com.example.languageapp.data.sources.local.KeyValueDataRepository
import com.example.languageapp.data.sources.network.QuestionDataRepository
import com.example.languageapp.data.sources.network.responses.WordPracticeQuestion

class QuestionRepositoryImpl (
    private val keyValueRepository: KeyValueDataRepository,
    private val questionRepository: QuestionDataRepository
): QuestionRepository {
    override suspend fun getGuessTheAnimal(): Result<GuessTheAnimalQuestion> {
        if (keyValueRepository.isUserAuthenticated()) {
            return questionRepository.getGuessTheAnimal(keyValueRepository.email!!)
        }
        return Result.failure(DataException(ExceptionReason.UserNotAuthenticated))
    }

    override suspend fun getWordPractice(): Result<WordPracticeQuestion> {
        if (keyValueRepository.isUserAuthenticated()) {
            return questionRepository.getWordPractice(keyValueRepository.email!!)
        }
        return Result.failure(DataException(ExceptionReason.UserNotAuthenticated))
    }

    override suspend fun getAudition(): Result<Word> {
        if (keyValueRepository.isUserAuthenticated()) {
            return questionRepository.getAudition(keyValueRepository.email!!)
        }
        return Result.failure(DataException(ExceptionReason.UserNotAuthenticated))
    }
}