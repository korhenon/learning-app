package com.example.languageapp.presentation.screens.guessTheAnimal

sealed interface GuessTheAnimalAction {
    data object Back : GuessTheAnimalAction
    data object Check : GuessTheAnimalAction
    data class ChangeAnswer(val value: String): GuessTheAnimalAction
}