package com.example.languageapp.presentation.screens.guessTheAnimalResult

import com.example.languageapp.presentation.screens.guessTheAnimal.GuessTheAnimalAction

sealed interface GuessTheAnimalResultAction {
    data object Back : GuessTheAnimalResultAction
    data object Next : GuessTheAnimalResultAction
    data object TryAgain : GuessTheAnimalResultAction
}