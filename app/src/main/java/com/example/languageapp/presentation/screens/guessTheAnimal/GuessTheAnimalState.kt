package com.example.languageapp.presentation.screens.guessTheAnimal

import com.example.languageapp.data.models.GuessTheAnimalQuestion
import com.example.languageapp.presentation.utils.InternetState

data class GuessTheAnimalState(
    val question: GuessTheAnimalQuestion = GuessTheAnimalQuestion(image = ""),
    val answer: String = "",
    val internetState: InternetState = InternetState()
)
