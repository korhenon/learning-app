package com.example.languageapp.data.sources.network

import com.example.languageapp.data.exceptions.DataException
import com.example.languageapp.data.exceptions.ExceptionReason
import com.example.languageapp.data.sources.network.requests.LoginRequest
import com.example.languageapp.data.sources.network.requests.RegisterRequest
import com.example.languageapp.data.sources.network.responses.UserInfo
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class AuthDataRepositoryImpl(
    private val service: AuthService
) : AuthDataRepository {
    override suspend fun login(email: String, password: String): Throwable? {
        try {
            service.login(LoginRequest(email, password))
            return null
        } catch (e: UnknownHostException) {
            return DataException(ExceptionReason.NoInternet, e)
        } catch (e: HttpException) {
            return DataException(ExceptionReason.BadRequest, e)
        }catch (e: SocketTimeoutException) {
            return DataException(ExceptionReason.ServerIsSleeping, e)
        }
    }

    override suspend fun getUserInfo(email: String): Result<UserInfo> {
        try {
            val userInfo = service.getUserInfo(email)
            return Result.success(userInfo)
        } catch (e: UnknownHostException) {
            return Result.failure(DataException(ExceptionReason.NoInternet, e))
        } catch (e: HttpException) {
            return Result.failure(DataException(ExceptionReason.BadRequest, e))
        } catch (e: SocketTimeoutException) {
            return Result.failure(DataException(ExceptionReason.ServerIsSleeping, e))
        }
    }

    override suspend fun signUp(
        firstName: String,
        secondName: String,
        email: String,
        password: String
    ): Throwable? {
        try {
            service.register(RegisterRequest(email, password, firstName, secondName))
            return null
        } catch (e: UnknownHostException) {
            return DataException(ExceptionReason.NoInternet, e)
        } catch (e: HttpException) {
            return DataException(ExceptionReason.BadRequest, e)
        }catch (e: SocketTimeoutException) {
            return DataException(ExceptionReason.ServerIsSleeping, e)
        }
    }
}