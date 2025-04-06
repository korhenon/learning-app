package com.example.languageapp.presentation.screens.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.languageapp.domain.navigation.Destination
import com.example.languageapp.domain.navigation.Navigator
import com.example.languageapp.domain.usecase.GetStartOnboardingPageUseCase
import com.example.languageapp.domain.usecase.NextOnboardingPageUseCase
import com.example.languageapp.domain.usecase.SkipOnboardingUseCase
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
    private val navigator: Navigator,
    private val nextOnboardingPageUseCase: NextOnboardingPageUseCase,
    private val skipOnboardingUseCase: SkipOnboardingUseCase,
    private val getStartOnboardingPageUseCase: GetStartOnboardingPageUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(OnboardingState())
    val state = _state
        .onStart { load() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), OnboardingState())

    private suspend fun load() {
        _state.update {
            it.copy(page = getStartOnboardingPageUseCase.invoke())
        }
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
            skipOnboardingUseCase.invoke()
        }
    }

    private fun next() {
        viewModelScope.launch {
            if (_state.value.page < 2) {
                _state.update { it.copy(page = it.page + 1) }
                nextOnboardingPageUseCase.invoke()
            } else {
                navigator.navigate(Destination.SelectLanguage)
            }
        }
    }
}