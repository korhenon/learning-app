package com.example.languageapp.presentation.screens.choosePassword

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.languageapp.common.isPasswordValid
import com.example.languageapp.data.exceptions.DataException
import com.example.languageapp.data.exceptions.ExceptionReason
import com.example.languageapp.domain.usecase.GetSignUpSavedStateUseCase
import com.example.languageapp.domain.usecase.SignUpUseCase
import com.example.languageapp.domain.usecase.UpdateSignUpSavedStateUseCase
import com.example.languageapp.domain.navigation.Destination
import com.example.languageapp.domain.navigation.Navigator
import com.example.languageapp.presentation.utils.InternetState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ChoosePasswordViewModel @Inject constructor(
    private val navigator: Navigator,
    private val savedStateHandle: SavedStateHandle,
    private val getSignUpSavedStateUseCase: GetSignUpSavedStateUseCase,
    private val updateSignUpSavedStateUseCase: UpdateSignUpSavedStateUseCase,
    private val signUpUseCase: SignUpUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _state = MutableStateFlow(ChoosePasswordState())
    val state = _state.onStart { load() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), ChoosePasswordState())

    private fun load() {
        viewModelScope.launch {
            val route = savedStateHandle.toRoute<Destination.ChoosePassword>()
            val savedState = getSignUpSavedStateUseCase.invoke()
            _state.update {
                it.copy(
                    firstName = route.firstName,
                    lastName = route.lastName,
                    email = route.email,
                    password = savedState.password,
                    confirmPassword = savedState.confirmPassword
                )
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            navigator.navigate(Destination.Login)
        }
    }

    private fun changePassword(value: String) {
        viewModelScope.launch {
            updateSignUpSavedStateUseCase.invoke(password = value)
        }
        _state.update {
            it.copy(password = value, isPasswordValid = value.isPasswordValid())
        }
    }

    private fun changeConfirmPassword(value: String) {
        viewModelScope.launch {
            updateSignUpSavedStateUseCase.invoke(confirmPassword = value)
        }
        _state.update {
            it.copy(confirmPassword = value)
        }
    }

    private fun changePrivacy(value: Boolean) {
        _state.update {
            it.copy(acceptPrivacy = value)
        }
    }

    private fun back() {
        viewModelScope.launch {
            navigator.navigateUp()
        }
    }

    private fun openPrivacy() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val file = File(context.filesDir, "Политика конфединциальности")
                val inputStream = context.assets.open("privacy.pdf")
                val outputStream = file.outputStream()
                inputStream.use { input ->
                    outputStream.use { output ->
                        input.copyTo(output)
                    }
                }
                withContext(Dispatchers.Main) {
                    val uri =
                        FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        setDataAndType(uri, "application/pdf")
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    private fun updateInternetState(internetState: InternetState) {
        _state.update { it.copy(internetState = internetState) }
    }

    private fun signUp() {
        viewModelScope.launch {
            if (_state.value.password.isNotEmpty() && _state.value.isPasswordValid && _state.value.acceptPrivacy) {
                if (_state.value.password == _state.value.confirmPassword) {
                    updateInternetState(
                        _state.value.internetState.copy(
                            isLoading = true,
                            isNoInternet = false
                        )
                    )
                    val signUpResult = signUpUseCase.invoke(
                        _state.value.firstName,
                        _state.value.lastName,
                        _state.value.email,
                        _state.value.password
                    ) as DataException?
                    if (signUpResult == null) {
                        navigator.navigate(Destination.Login)
                    } else if (signUpResult.exceptionReason == ExceptionReason.NoInternet) {
                        updateInternetState(
                            _state.value.internetState.copy(
                                isNoInternet = true
                            )
                        )
                    }
                    updateInternetState(
                        _state.value.internetState.copy(
                            isLoading = false
                        )
                    )
                } else {
                    Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun onAction(action: ChoosePasswordAction) {
        when (action) {
            ChoosePasswordAction.Back -> back()
            is ChoosePasswordAction.ChangeConfirmPassword -> changeConfirmPassword(action.value)
            is ChoosePasswordAction.ChangePassword -> changePassword(action.value)
            ChoosePasswordAction.Login -> login()
            ChoosePasswordAction.Signup -> signUp()
            is ChoosePasswordAction.ChangePrivacy -> changePrivacy(action.value)
            ChoosePasswordAction.OpenPrivacy -> openPrivacy()
        }
    }
}