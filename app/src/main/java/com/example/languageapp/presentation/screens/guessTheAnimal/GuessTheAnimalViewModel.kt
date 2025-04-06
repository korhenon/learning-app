package com.example.languageapp.presentation.screens.guessTheAnimal

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.languageapp.data.exceptions.DataException
import com.example.languageapp.data.exceptions.ExceptionReason
import com.example.languageapp.domain.navigation.Destination
import com.example.languageapp.domain.navigation.Navigator
import com.example.languageapp.domain.usecase.GetGuessTheAnimalUseCase
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
class GuessTheAnimalViewModel @Inject constructor(
    private val navigator: Navigator,
    private val savedStateHandle: SavedStateHandle,
    private val getGuessTheAnimalUseCase: GetGuessTheAnimalUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(GuessTheAnimalState())
    val state = _state.onStart { load() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), GuessTheAnimalState())

    private suspend fun load() {
        val result = getGuessTheAnimalUseCase.invoke()

        updateInternetState(
            _state.value.internetState.copy(
                isLoading = true,
                isNoInternet = false
            )
        )
        if (result.isSuccess) {
            _state.update {
                it.copy(question = result.getOrNull()!!)
            }
        } else {
            val exception = result.exceptionOrNull() as DataException
            if (exception.exceptionReason == ExceptionReason.NoInternet) {
                updateInternetState(
                    _state.value.internetState.copy(
                        isNoInternet = true
                    )
                )
            }
        }
        updateInternetState(
            _state.value.internetState.copy(
                isLoading = false
            )
        )
    }

    private fun updateInternetState(internetState: InternetState) {
        _state.update { it.copy(internetState = internetState) }
    }

    private fun back() {
        viewModelScope.launch {
            navigator.navigateUp()
        }
    }

    private fun check() {
        viewModelScope.launch {
            val rightAnswer = "Racoon" // TODO: Add logic for getting right result
            var streak = savedStateHandle.toRoute<Destination.GuessTheAnimal>().streak
            if (_state.value.answer == rightAnswer) {
                streak++
            } else {
                streak = 0
            }
            navigator.navigate(
                Destination.GuessTheAnimalResult(
                    answer = _state.value.answer,
                    rightAnswer = rightAnswer,
                    streak = streak
                )
            )
        }
    }

    private fun changeAnswer(value: String) {
        _state.update {
            it.copy(answer = value)
        }
    }

    fun onAction(action: GuessTheAnimalAction) {
        when (action) {
            GuessTheAnimalAction.Back -> back()
            GuessTheAnimalAction.Check -> check()
            is GuessTheAnimalAction.ChangeAnswer -> changeAnswer(action.value)
        }
    }
}