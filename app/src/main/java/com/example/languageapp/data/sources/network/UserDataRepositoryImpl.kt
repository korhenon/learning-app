package com.example.languageapp.data.sources.network

import com.example.languageapp.data.exceptions.DataException
import com.example.languageapp.data.exceptions.ExceptionReason
import com.example.languageapp.data.sources.network.responses.UserTopInfo
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class UserDataRepositoryImpl(
    private val service: UserService
) : UserDataRepository {
    override suspend fun getUserTop(): Result<List<UserTopInfo>> {
        try {
            val response = service.userTop()
            return Result.success(response)
        } catch (e: UnknownHostException) {
            return Result.failure(DataException(ExceptionReason.NoInternet, e))
        } catch (e: SocketTimeoutException) {
            return Result.failure(DataException(ExceptionReason.ServerIsSleeping, e))
        }
    }
}