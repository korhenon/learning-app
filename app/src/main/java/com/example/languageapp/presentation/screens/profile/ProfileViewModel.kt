package com.example.languageapp.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.languageapp.domain.usecase.GetCurrentUserUseCase
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
class ProfileViewModel @Inject constructor(
    private val navigator: Navigator,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(ProfileState())
    val state = _state.onStart { load() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), ProfileState())

    private suspend fun load() {
        updateInternetState(_state.value.internetState.copy(isLoading = true, isNoInternet = false))
        val userResult = getCurrentUserUseCase.invoke()
        if (userResult.isSuccess) {
            _state.update {
                it.copy(currentUser = userResult.getOrNull()!!)
            }
        } else {
            updateInternetState(_state.value.internetState.copy(isNoInternet = true))
        }
        updateInternetState(_state.value.internetState.copy(isLoading = false))
    }

    private fun updateInternetState(internetState: InternetState) {
        _state.update { it.copy(internetState = internetState) }
    }

    private fun changeLanguage() {
        viewModelScope.launch {
            navigator.navigate(Destination.SelectLanguage)
        }
    }

    fun onAction(action: ProfileAction) {
        when (action) {
            ProfileAction.SwitchToDark -> TODO()
            ProfileAction.ChangeLanguage -> changeLanguage()
            ProfileAction.ChangeImage -> TODO()
            ProfileAction.Logout -> TODO()
        }
    }
}