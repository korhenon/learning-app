package com.example.languageapp.presentation.screens.home

import com.example.languageapp.data.models.User

data class HomeState(
    val name: String = "",
    val photo: String = "",
    val topUsers: List<User> = listOf()
)
