package com.example.languageapp.presentation.screens.home

import com.example.languageapp.data.models.TopUser
import com.example.languageapp.data.models.User
import com.example.languageapp.presentation.utils.InternetState

data class HomeState(
    val currentUser: User = User("", ""),
    val topUsers: List<TopUser> = listOf(),
    val internetState: InternetState = InternetState()
)
