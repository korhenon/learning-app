package com.example.languageapp.data.sources.network

import com.example.languageapp.data.models.GuessTheAnimalQuestion
import com.example.languageapp.data.models.Word
import com.example.languageapp.data.sources.network.responses.WordPracticeQuestion
import retrofit2.http.GET
import retrofit2.http.Path

interface QuestionsService {
    @GET("animal/{email}")
    suspend fun guessTheAnimal(@Path("email") email: String): GuessTheAnimalQuestion

    @GET("word-practice/{email}")
    suspend fun wordPractice(@Path("email") email: String): WordPracticeQuestion


    @GET("audition/{email}")
    suspend fun audition(@Path("email") email: String): Word

}