package com.example.languageapp.data.sources.network

import com.example.languageapp.data.sources.network.responses.UserTopInfo

interface UserDataRepository {
    suspend fun getUserTop(): Result<List<UserTopInfo>>
}