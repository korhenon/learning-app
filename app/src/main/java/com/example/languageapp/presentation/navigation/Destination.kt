package com.example.languageapp.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object Onboarding : Destination

    @Serializable
    data object SelectLanguage : Destination

    @Serializable
    data object Login : Destination

    @Serializable
    data object Signup : Destination

    @Serializable
    data class ChoosePassword(
        val firstName: String,
        val lastName: String,
        val email: String
    ) : Destination

    @Serializable
    data object Home : Destination

    @Serializable
    data object Profile : Destination

    @Serializable
    data class GuessTheAnimal(val streak: Int = 0) : Destination

    @Serializable
    data class GuessTheAnimalResult(
        val answer: String,
        val rightAnswer: String,
        val streak: Int = 0
    ) : Destination

    @Serializable
    data class WordPractice(val streak: Int = 0) : Destination

    @Serializable
    data class Audition(val streak: Int = 0) : Destination

    @Serializable
    data class Game(val streak: Int = 0) : Destination
}