package com.example.languageapp.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object Onboarding: Destination

    @Serializable
    data object SelectLanguage : Destination
}