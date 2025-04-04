package com.example.languageapp.presentation.screens.audition

sealed interface AuditionAction {
    data object Back: AuditionAction
    data object CheckSpeech: AuditionAction
    data object Next: AuditionAction
    data object NoPermissions : AuditionAction
}