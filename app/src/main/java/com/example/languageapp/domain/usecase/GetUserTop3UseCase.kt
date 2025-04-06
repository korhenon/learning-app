package com.example.languageapp.domain.usecase

import com.example.languageapp.data.models.TopUser
import com.example.languageapp.domain.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserTop3UseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun invoke(): Result<List<TopUser>> {
        return withContext(Dispatchers.IO) {
            return@withContext userRepository.getUserTop3()
        }
    }
}