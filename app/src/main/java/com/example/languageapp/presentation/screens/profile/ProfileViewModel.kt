package com.example.languageapp.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.languageapp.presentation.navigation.Destination
import com.example.languageapp.presentation.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val navigator: Navigator) : ViewModel() {
    private val _state = MutableStateFlow(ProfileState())
    val state = _state.onStart { }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), ProfileState())

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