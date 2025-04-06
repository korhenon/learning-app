package com.example.languageapp.presentation.screens.audition

import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.languageapp.data.exceptions.DataException
import com.example.languageapp.data.exceptions.ExceptionReason
import com.example.languageapp.presentation.CustomRecognitionListener
import com.example.languageapp.domain.navigation.Destination
import com.example.languageapp.domain.navigation.Navigator
import com.example.languageapp.domain.usecase.GetAuditionUseCase
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
class AuditionViewModel @Inject constructor(
    private val navigator: Navigator,
    private val savedStateHandle: SavedStateHandle,
    private val getAuditionUseCase: GetAuditionUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _state = MutableStateFlow(AuditionState())
    val state = _state.onStart { load() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), AuditionState())
    private val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)

    init {
        speechRecognizer.setRecognitionListener(
            CustomRecognitionListener(
                onRecognitionSuccess = { onRecognitionSuccess(it) },
                onRecognitionError = { onRecognitionError(it) }
            )
        )
    }

    private suspend fun load() {
        val result = getAuditionUseCase.invoke()

        updateInternetState(
            _state.value.internetState.copy(
                isLoading = true,
                isNoInternet = false
            )
        )
        if (result.isSuccess) {
            _state.update {
                it.copy(question = result.getOrNull()!!)
            }
        } else {
            val exception = result.exceptionOrNull() as DataException
            if (exception.exceptionReason == ExceptionReason.NoInternet) {
                updateInternetState(
                    _state.value.internetState.copy(
                        isNoInternet = true
                    )
                )
            }
        }
        updateInternetState(
            _state.value.internetState.copy(
                isLoading = false
            )
        )
    }

    private fun updateInternetState(internetState: InternetState) {
        _state.update { it.copy(internetState = internetState) }
    }

    private fun onRecognitionSuccess(text: String) {
        _state.update {
            it.copy(
                recognitionResults = it.recognitionResults + listOf(text),
                isRight = text == it.question.word
            )
        }
    }

    private fun onRecognitionError(error: Int) {
        _state.update {
            it.copy(
                recognitionResults = it.recognitionResults + listOf("Не распознано"),
                isRight = false
            )
        }
    }


    private fun back() {
        viewModelScope.launch {
            navigator.navigate(Destination.Home)
        }
    }

    private fun next() {
        viewModelScope.launch {
            val route = savedStateHandle.toRoute<Destination.Audition>()
            val streak = if (_state.value.recognitionResults.size == 1) route.streak + 1 else 1
            navigator.navigate(Destination.Audition(streak = streak))
        }
    }

    private fun checkSpeech() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en")
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Говорите...")
        speechRecognizer.startListening(intent)
    }

    private fun showMessage() {
        Toast.makeText(
            context,
            "Для работы аудирования нужно предоставить разрешение на запись звука",
            Toast.LENGTH_LONG
        ).show()
    }

    fun onAction(action: AuditionAction) {
        when (action) {
            AuditionAction.Back -> back()
            AuditionAction.CheckSpeech -> checkSpeech()
            AuditionAction.Next -> next()
            AuditionAction.NoPermissions -> showMessage()
        }
    }

    override fun onCleared() {
        super.onCleared()
        speechRecognizer.destroy()
    }
}