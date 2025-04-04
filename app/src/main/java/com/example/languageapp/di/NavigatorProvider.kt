package com.example.languageapp.di

import com.example.languageapp.presentation.navigation.DefaultNavigator
import com.example.languageapp.presentation.navigation.Destination
import com.example.languageapp.presentation.navigation.Navigator
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
    fun provideDefaultNavigator(): Navigator {
        return DefaultNavigator(Destination.Onboarding)
    }
}