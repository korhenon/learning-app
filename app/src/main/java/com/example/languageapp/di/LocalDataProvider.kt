package com.example.languageapp.di

import android.content.Context
import com.example.languageapp.data.sources.local.KeyValueDataRepository
import com.example.languageapp.data.sources.local.KeyValueService
import com.example.languageapp.data.sources.local.KeyValueDataRepositoryImpl
import com.example.languageapp.data.sources.local.SharedPreferencesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalDataProvider {
    @Provides
    fun provideKeyValueService(@ApplicationContext context: Context): KeyValueService {
        return SharedPreferencesService(context)
    }

    @Provides
    fun provideKeyValueRepository(service: KeyValueService): KeyValueDataRepository {
        return KeyValueDataRepositoryImpl(service)
    }
}