package com.example.languageapp.presentation.screens.home

import com.example.languageapp.data.models.ExerciseType

sealed interface HomeAction {
    data object OpenProfile: HomeAction
    data class OpenExercise(val type: ExerciseType): HomeAction
}