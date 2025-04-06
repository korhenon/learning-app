package com.example.languageapp.di

import com.example.languageapp.common.BASE_URL
import com.example.languageapp.data.sources.network.AuthDataRepository
import com.example.languageapp.data.sources.network.AuthDataRepositoryImpl
import com.example.languageapp.data.sources.network.AuthService
import com.example.languageapp.data.sources.network.QuestionDataRepository
import com.example.languageapp.data.sources.network.QuestionDataRepositoryImpl
import com.example.languageapp.data.sources.network.QuestionsService
import com.example.languageapp.data.sources.network.UserDataRepository
import com.example.languageapp.data.sources.network.UserDataRepositoryImpl
import com.example.languageapp.data.sources.network.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkDataProvider {
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    fun provideAuthService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    fun provideAuthDataRepository(service: AuthService): AuthDataRepository {
        return AuthDataRepositoryImpl(service)
    }

    @Provides
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    fun provideUserDataRepository(service: UserService): UserDataRepository {
        return UserDataRepositoryImpl(service)
    }

    @Provides
    fun provideQuestionService(retrofit: Retrofit): QuestionsService {
        return retrofit.create(QuestionsService::class.java)
    }

    @Provides
    fun provideQuestionDataRepository(service: QuestionsService): QuestionDataRepository {
        return QuestionDataRepositoryImpl(service)
    }
}