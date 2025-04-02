package com.example.languageapp.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.languageapp.common.isEmailValid
import com.example.languageapp.common.isPasswordValid
import com.example.languageapp.ui.navigation.Destination
import com.example.languageapp.ui.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigator: Navigator
): ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.onStart {  }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), LoginState())

    fun onAction(action: LoginAction) {
        when (action) {
            LoginAction.Back -> back()
            LoginAction.Login -> TODO()
            LoginAction.SignUp -> signUp()
            is LoginAction.ChangeEmail -> changeEmail(action.value)
            is LoginAction.ChangePassword -> changePassword(action.value)
        }
    }

    private fun changeEmail(value: String) {
        _state.update {
            it.copy(email = value, isEmailValid = value.isEmailValid())
        }
    }

    private fun changePassword(value: String) {
        _state.update {
            it.copy(password = value, isPasswordValid = value.isPasswordValid())
        }
    }

    private fun back() {
        viewModelScope.launch { navigator.navigate(Destination.SelectLanguage) }
    }

    private fun signUp() {
        viewModelScope.launch { navigator.navigate(Destination.Signup) }
    }
}