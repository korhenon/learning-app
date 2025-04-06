package com.example.languageapp.di

import com.example.languageapp.domain.navigation.DefaultNavigator
import com.example.languageapp.domain.navigation.Destination
import com.example.languageapp.domain.navigation.Navigator
import com.example.languageapp.domain.usecase.GetStartDestinationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavigatorProvider {
    @Singleton
    @Provides
    fun provideDefaultNavigator(getStartDestinationUseCase: GetStartDestinationUseCase): Navigator {
        return DefaultNavigator(getStartDestinationUseCase.invoke())
    }
}