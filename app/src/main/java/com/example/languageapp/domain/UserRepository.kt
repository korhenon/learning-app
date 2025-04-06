package com.example.languageapp.domain

import com.example.languageapp.data.models.TopUser

interface UserRepository {
    suspend fun getUserTop3(): Result<List<TopUser>>

    suspend fun getTopForCurrentUser(): Result<List<TopUser>>
}