package com.example.languageapp.di

import com.example.languageapp.data.sources.local.KeyValueDataRepository
import com.example.languageapp.data.sources.network.AuthDataRepository
import com.example.languageapp.data.sources.network.QuestionDataRepository
import com.example.languageapp.data.sources.network.UserDataRepository
import com.example.languageapp.domain.AppRepository
import com.example.languageapp.domain.AppRepositoryImpl
import com.example.languageapp.domain.AuthRepository
import com.example.languageapp.domain.AuthRepositoryImpl
import com.example.languageapp.domain.QuestionRepository
import com.example.languageapp.domain.QuestionRepositoryImpl
import com.example.languageapp.domain.UserRepository
import com.example.languageapp.domain.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainProvider {
    @Provides
    fun provideAuthRepository(
        keyValueDataRepository: KeyValueDataRepository,
        authDataRepository: AuthDataRepository
    ): AuthRepository {
        return AuthRepositoryImpl(keyValueDataRepository, authDataRepository)
    }

    @Provides
    fun provideUserRepository(
        keyValueDataRepository: KeyValueDataRepository,
        userRepository: UserDataRepository
    ): UserRepository {
        return UserRepositoryImpl(userRepository, keyValueDataRepository)
    }

    @Provides
    fun provideAppRepository(keyValueDataRepository: KeyValueDataRepository): AppRepository {
        return AppRepositoryImpl(keyValueDataRepository)
    }

    @Provides
    fun provideQuestionRepository(keyValueDataRepository: KeyValueDataRepository, questionDataRepository: QuestionDataRepository): QuestionRepository {
        return QuestionRepositoryImpl(keyValueDataRepository, questionDataRepository)
    }
}