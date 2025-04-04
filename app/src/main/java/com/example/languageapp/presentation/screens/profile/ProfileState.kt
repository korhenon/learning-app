package com.example.languageapp.presentation.screens.profile

import com.example.languageapp.data.models.User

data class ProfileState(
    val currentUser: User = User("", "", 0f),

)
