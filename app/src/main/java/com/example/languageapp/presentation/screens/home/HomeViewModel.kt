package com.example.languageapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.languageapp.data.models.ExerciseType
import com.example.languageapp.domain.usecase.GetCurrentUserUseCase
import com.example.languageapp.domain.usecase.GetUserTop3UseCase
import com.example.languageapp.domain.navigation.Destination
import com.example.languageapp.domain.navigation.Navigator
import com.example.languageapp.presentation.utils.InternetState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigator: Navigator,
    private val getUserTop3UseCase: GetUserTop3UseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.onStart {
        load()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), HomeState())

    private suspend fun load() {
        updateInternetState(_state.value.internetState.copy(isLoading = true, isNoInternet = false))
        val userResult = getCurrentUserUseCase.invoke()
        val topResult = getUserTop3UseCase.invoke()

        if (userResult.isSuccess && topResult.isSuccess) {
            _state.update {
                it.copy(currentUser = userResult.getOrNull()!!, topUsers = topResult.getOrNull()!!)
            }
        } else {
            updateInternetState(_state.value.internetState.copy(isNoInternet = true))
        }
        updateInternetState(_state.value.internetState.copy(isLoading = false))
    }

    private fun updateInternetState(internetState: InternetState) {
        _state.update { it.copy(internetState = internetState) }
    }

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