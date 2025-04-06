package com.example.languageapp.presentation.screens.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.languageapp.common.isEmailValid
import com.example.languageapp.domain.usecase.GetSignUpSavedStateUseCase
import com.example.languageapp.domain.usecase.UpdateSignUpSavedStateUseCase
import com.example.languageapp.domain.navigation.Destination
import com.example.languageapp.domain.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val navigator: Navigator,
    private val getSignUpSavedStateUseCase: GetSignUpSavedStateUseCase,
    private val updateSignUpSavedStateUseCase: UpdateSignUpSavedStateUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(SignUpState())
    val state = _state.onStart { load() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), SignUpState())

    private suspend fun load() {
        val savedState = getSignUpSavedStateUseCase.invoke()
        _state.update {
            it.copy(
                firstName = savedState.firstName,
                lastName = savedState.secondName,
                email = savedState.email
            )
        }
    }

    fun onAction(action: SignUpAction) {
        when (action) {
            SignUpAction.Back -> login()
            is SignUpAction.ChangeEmail -> changeEmail(action.value)
            is SignUpAction.ChangeFirstName -> changeFirstName(action.value)
            is SignUpAction.ChangeLastName -> changeLastName(action.value)
            SignUpAction.Continue -> nextScreen()
            SignUpAction.Login -> login()
        }
    }

    private fun changeFirstName(value: String) {
        viewModelScope.launch {
            updateSignUpSavedStateUseCase.invoke(firstName = value)
        }
        _state.update {
            it.copy(firstName = value)
        }
    }

    private fun changeLastName(value: String) {
        viewModelScope.launch {
            updateSignUpSavedStateUseCase.invoke(secondName = value)
        }
        _state.update {
            it.copy(lastName = value)
        }
    }

    private fun changeEmail(value: String) {
        viewModelScope.launch {
            updateSignUpSavedStateUseCase.invoke(email = value)
        }
        _state.update {
            it.copy(email = value, isEmailValid = value.isEmailValid())
        }
    }

    private fun login() {
        viewModelScope.launch { navigator.navigate(Destination.Login) }
    }

    private fun nextScreen() {
        viewModelScope.launch {
            if (
                _state.value.firstName.isNotEmpty() &&
                _state.value.lastName.isNotEmpty() &&
                _state.value.isEmailValid &&
                _state.value.email.isNotEmpty()
            ) {
                navigator.navigate(
                    Destination.ChoosePassword(
                        _state.value.firstName,
                        _state.value.lastName,
                        _state.value.email
                    )
                )
            }
        }
    }
}