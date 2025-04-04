package com.example.languageapp.presentation

import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer

class CustomRecognitionListener(
    private val onRecognitionSuccess: (String) -> Unit,
    private val onRecognitionError: (Int) -> Unit
) : RecognitionListener {
    override fun onResults(results: Bundle?) {
        onRecognitionSuccess(
            results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.get(0) ?: ""
        )
    }

    override fun onError(err: Int) {
        onRecognitionError(err)
    }

    override fun onReadyForSpeech(p0: Bundle?) {}
    override fun onBeginningOfSpeech() {}
    override fun onRmsChanged(p0: Float) {}
    override fun onBufferReceived(p0: ByteArray?) {}
    override fun onEndOfSpeech() {}
    override fun onPartialResults(p0: Bundle?) {}
    override fun onEvent(p0: Int, p1: Bundle?) {}
}