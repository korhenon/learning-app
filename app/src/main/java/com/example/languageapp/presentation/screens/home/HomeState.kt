package com.example.languageapp.presentation.screens.home

import com.example.languageapp.data.models.User

data class HomeState(
    val currentUser: User = User("", "", 0f),
    val topUsers: List<User> = listOf()
)
