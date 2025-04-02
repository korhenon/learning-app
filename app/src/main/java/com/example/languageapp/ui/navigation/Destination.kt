package com.example.languageapp.ui.navigation

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
}