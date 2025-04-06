package com.example.languageapp.presentation.screens.login

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.languageapp.common.isEmailValid
import com.example.languageapp.data.exceptions.DataException
import com.example.languageapp.data.exceptions.ExceptionReason
import com.example.languageapp.domain.usecase.LoginUseCase
import com.example.languageapp.domain.navigation.Destination
import com.example.languageapp.domain.navigation.Navigator
import com.example.languageapp.presentation.utils.InternetState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigator: Navigator,
    private val loginUseCase: LoginUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state =
        _state.onStart { }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), LoginState())

    fun onAction(action: LoginAction) {
        when (action) {
            LoginAction.Back -> back()
            LoginAction.Login -> login()
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
            it.copy(password = value)
        }
    }

    private fun back() {
        viewModelScope.launch { navigator.navigate(Destination.SelectLanguage) }
    }

    private fun signUp() {
        viewModelScope.launch { navigator.navigate(Destination.Signup) }
    }

    private fun updateInternetState(internetState: InternetState) {
        _state.update { it.copy(internetState = internetState) }
    }

    private fun login() {
        viewModelScope.launch {
            if (_state.value.isEmailValid) {
                updateInternetState(_state.value.internetState.copy(
                    isLoading = true,
                    isNoInternet = false
                ))
                val useCaseResult = loginUseCase.invoke(_state.value.email, _state.value.password) as DataException?
                if (useCaseResult == null) {
                    navigator.navigate(Destination.Home)
                } else {
                    if (useCaseResult.exceptionReason == ExceptionReason.NoInternet) {
                        updateInternetState(_state.value.internetState.copy(
                            isNoInternet = true
                        ))
                    } else {
                        Toast.makeText(context, "Check your login data", Toast.LENGTH_SHORT).show()
                    }
                }
                updateInternetState(_state.value.internetState.copy(
                    isLoading = false
                ))
            } else {
                Toast.makeText(context, "Email is not valid", Toast.LENGTH_SHORT).show()
            }
        }
    }
}