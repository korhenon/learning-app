package com.example.languageapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.languageapp.data.models.ExerciseType
import com.example.languageapp.presentation.navigation.Destination
import com.example.languageapp.presentation.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.onStart {

    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), HomeState())

    private fun profile() {
        viewModelScope.launch {
            navigator.navigate(Destination.Profile)
        }
    }

    private fun openExercise(type: ExerciseType) {
        viewModelScope.launch {
            when (type) {
                ExerciseType.GuessTheAnimal -> navigator.navigate(Destination.GuessTheAnimal())
                ExerciseType.WordPractice -> navigator.navigate(Destination.WordPractice())
                ExerciseType.Audition -> navigator.navigate(Destination.Audition())
                ExerciseType.Game -> navigator.navigate(Destination.Game())
            }
        }
    }

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.OpenExercise -> openExercise(action.type)
            HomeAction.OpenProfile -> profile()
        }
    }
}