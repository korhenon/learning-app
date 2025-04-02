package com.example.languageapp.ui.screens.selectLanguage

import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class SelectLanguageViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {
    private val _state = MutableStateFlow(SelectLanguageState())
    val state = _state.onStart { load() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), SelectLanguageState())

    private fun load() {
        val userLanguage = Locale.current.platformLocale.displayLanguage
        if (userLanguage == "русский") onAction(SelectLanguageAction.ChangeLanguage("Russian"))
        else onAction(SelectLanguageAction.ChangeLanguage("English"))
    }

    fun chooseLanguage() {
        viewModelScope.launch {
            navigator.navigate(Destination.Login)
        }
    }

    fun onAction(action: SelectLanguageAction) {
        when (action) {
            is SelectLanguageAction.ChangeLanguage -> changeLanguage(action.selectedLanguage)
            SelectLanguageAction.Choose -> chooseLanguage()
        }
    }

    private fun changeLanguage(selectedLanguage: String) {
        _state.update {
            it.copy(selectedLanguage = selectedLanguage)
        }
    }
}