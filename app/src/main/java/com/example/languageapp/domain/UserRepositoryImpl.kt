package com.example.languageapp.domain

import com.example.languageapp.data.models.TopUser
import com.example.languageapp.data.models.User
import com.example.languageapp.data.sources.local.KeyValueDataRepository
import com.example.languageapp.data.sources.network.UserDataRepository

class UserRepositoryImpl(
    private val userDataRepository: UserDataRepository,
    private val keyValueRepository: KeyValueDataRepository
) : UserRepository {
    override suspend fun getUserTop3(): Result<List<TopUser>> {
        val result = userDataRepository.getUserTop()
        return result.map {
            it.take(3).map { topUser ->
                TopUser(
                    user = User(
                        name = topUser.firstName + " " + topUser.secondName,
                        photo = topUser.image
                    ), points = topUser.points
                )
            }
        }
    }

    override suspend fun getTopForCurrentUser(): Result<List<TopUser>> {
        TODO("Not yet implemented")
    }
}