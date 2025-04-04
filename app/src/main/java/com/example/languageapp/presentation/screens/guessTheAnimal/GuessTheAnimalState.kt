package com.example.languageapp.presentation.screens.guessTheAnimal

import com.example.languageapp.data.models.GuessTheAnimalQuestion

data class GuessTheAnimalState(
    val question: GuessTheAnimalQuestion = GuessTheAnimalQuestion(image = ""),
    val answer: String = ""
)
