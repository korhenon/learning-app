package com.example.languageapp.presentation.screens.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class OnboardingViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {
    private val _state = MutableStateFlow(OnboardingState())
    val state = _state
        .onStart { load() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), OnboardingState())

    private fun load() {

    }

    fun onAction(action: OnboardingAction) {
        when (action) {
            OnboardingAction.Next -> next()
            OnboardingAction.Skip -> skip()
        }
    }

    private fun skip() {
        viewModelScope.launch {
            navigator.navigate(Destination.SelectLanguage)
        }
    }

    private fun next() {
        viewModelScope.launch {
            if (_state.value.page < 2) {
                _state.update { it.copy(page = it.page + 1) }
            } else {
                navigator.navigate(Destination.SelectLanguage)
            }
        }
    }
}