package com.example.languageapp.data.sources.network.requests

data class RegisterRequest(
    val email: String,
    val password: String,
    val firstName: String,
    val secondName: String,
)
