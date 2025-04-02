package com.example.languageapp.ui.screens.choosePassword

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.languageapp.R
import com.example.languageapp.common.isPasswordValid
import com.example.languageapp.ui.navigation.Destination
import com.example.languageapp.ui.navigation.Navigator
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
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ChoosePasswordViewModel @Inject constructor(
    private val navigator: Navigator,
    private val savedStateHandle: SavedStateHandle,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _state = MutableStateFlow(ChoosePasswordState())
    val state = _state.onStart { load() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), ChoosePasswordState())

    private fun load() {
        val route = savedStateHandle.toRoute<Destination.ChoosePassword>()
        _state.update {
            it.copy(
                firstName = route.firstName,
                lastName = route.lastName,
                email = route.email
            )
        }
    }

    private fun login() {
        viewModelScope.launch {
            navigator.navigate(Destination.Login)
        }
    }

    private fun changePassword(value: String) {
        _state.update {
            it.copy(password = value, isPasswordValid = value.isPasswordValid())
        }
    }

    private fun changeConfirmPassword(value: String) {
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
                val file = File(context.filesDir, "privacy.pdf")
                val inputStream = BufferedInputStream(context.assets.open("privacy.pdf"))
                val outputStream = BufferedOutputStream(file.outputStream())
                inputStream.use { input ->
                    outputStream.use { output ->
                        output.write(input.read())
                    }
                }
                withContext(Dispatchers.Main) {
                    val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
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

    fun onAction(action: ChoosePasswordAction) {
        when (action) {
            ChoosePasswordAction.Back -> back()
            is ChoosePasswordAction.ChangeConfirmPassword -> changeConfirmPassword(action.value)
            is ChoosePasswordAction.ChangePassword -> changePassword(action.value)
            ChoosePasswordAction.Login -> login()
            ChoosePasswordAction.Signup -> TODO()
            is ChoosePasswordAction.ChangePrivacy -> changePrivacy(action.value)
            ChoosePasswordAction.OpenPrivacy -> openPrivacy()
        }
    }
}