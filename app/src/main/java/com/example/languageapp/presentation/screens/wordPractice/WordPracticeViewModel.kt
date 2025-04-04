package com.example.languageapp.presentation.screens.wordPractice

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.languageapp.data.models.Word
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
class WordPracticeViewModel @Inject constructor(
    private val navigator: Navigator,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(WordPracticeState())
    val state = _state.onStart { }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), WordPracticeState())

    private fun back() {
        viewModelScope.launch {
            navigator.navigate(Destination.Home)
        }
    }

    private fun check() {
        _state.update {
            it.copy(isChecked = true)
        }
    }

    private fun next() {
        viewModelScope.launch {
            var streak = savedStateHandle.toRoute<Destination.WordPractice>().streak
            if (_state.value.selected == _state.value.question) {
                streak++
            }
            else {
                streak = 0
            }
            navigator.navigate(Destination.WordPractice(streak=streak))
        }
    }

    private fun selectWord(word: Word) {
        _state.update {
            it.copy(selected = word)
        }
    }

    fun onAction(action: WordPracticeAction) {
        when(action) {
            WordPracticeAction.Back -> back()
            WordPracticeAction.Check -> check()
            WordPracticeAction.Next -> next()
            is WordPracticeAction.SelectWord -> selectWord(action.word)
        }
    }
}