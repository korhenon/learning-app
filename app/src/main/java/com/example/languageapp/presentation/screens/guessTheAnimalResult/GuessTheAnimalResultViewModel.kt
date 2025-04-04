package com.example.languageapp.presentation.screens.guessTheAnimalResult

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.languageapp.presentation.navigation.Destination
import com.example.languageapp.presentation.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuessTheAnimalResultViewModel @Inject constructor(
    private val navigator: Navigator,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(GuessTheAnimalResultState())
    val state = _state.onStart { load() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), GuessTheAnimalResultState())

    private fun load() {
        val route = savedStateHandle.toRoute<Destination.GuessTheAnimalResult>()
        _state.update {
            it.copy(isRight = route.answer == route.rightAnswer, rightAnswer = route.rightAnswer)
        }
    }

    private fun back() {
        viewModelScope.launch {
            navigator.navigate(Destination.Home)
        }
    }

    private fun next() {
        viewModelScope.launch {
            navigator.navigate(Destination.GuessTheAnimal(streak = savedStateHandle.toRoute<Destination.GuessTheAnimalResult>().streak))
        }
    }

    private fun tryAgain() {
        viewModelScope.launch {
            navigator.navigateUp()
        }
    }

    fun onAction(action: GuessTheAnimalResultAction) {
        when (action) {
            GuessTheAnimalResultAction.Back -> back()
            GuessTheAnimalResultAction.Next -> next()
            GuessTheAnimalResultAction.TryAgain -> tryAgain()
        }
    }
}