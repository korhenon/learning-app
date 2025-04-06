package com.example.languageapp.data.sources.network

import com.example.languageapp.data.exceptions.DataException
import com.example.languageapp.data.exceptions.ExceptionReason
import com.example.languageapp.data.models.GuessTheAnimalQuestion
import com.example.languageapp.data.models.Word
import com.example.languageapp.data.sources.network.responses.WordPracticeQuestion
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class QuestionDataRepositoryImpl (
    private val service: QuestionsService
): QuestionDataRepository {
    override suspend fun getGuessTheAnimal(email: String): Result<GuessTheAnimalQuestion> {
        try {
            val guessTheAnimal = service.guessTheAnimal(email)
            return Result.success(guessTheAnimal)
        } catch (e: UnknownHostException) {
            return Result.failure(DataException(ExceptionReason.NoInternet, e))
        } catch (e: HttpException) {
            return Result.failure(DataException(ExceptionReason.BadRequest, e))
        }catch (e: SocketTimeoutException) {
            return Result.failure(DataException(ExceptionReason.ServerIsSleeping, e))
        }
    }

    override suspend fun getWordPractice(email: String): Result<WordPracticeQuestion> {
        try {
            val wordPractice = service.wordPractice(email)
            return Result.success(wordPractice)
        } catch (e: UnknownHostException) {
            return Result.failure(DataException(ExceptionReason.NoInternet, e))
        } catch (e: HttpException) {
            return Result.failure(DataException(ExceptionReason.BadRequest, e))
        }catch (e: SocketTimeoutException) {
            return Result.failure(DataException(ExceptionReason.ServerIsSleeping, e))
        }
    }

    override suspend fun getAudition(email: String): Result<Word> {
        try {
            val audition = service.audition(email)
            return Result.success(audition)
        } catch (e: UnknownHostException) {
            return Result.failure(DataException(ExceptionReason.NoInternet, e))
        } catch (e: HttpException) {
            return Result.failure(DataException(ExceptionReason.BadRequest, e))
        }catch (e: SocketTimeoutException) {
            return Result.failure(DataException(ExceptionReason.ServerIsSleeping, e))
        }
    }
}