package com.example.languageapp.presentation.screens.profile

import com.example.languageapp.data.models.User
import com.example.languageapp.presentation.utils.InternetState

data class ProfileState(
    val currentUser: User = User("", ""),
    val internetState: InternetState = InternetState()
)
