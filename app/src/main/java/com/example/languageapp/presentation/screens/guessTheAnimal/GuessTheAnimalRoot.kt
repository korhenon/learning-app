package com.example.languageapp.presentation.screens.guessTheAnimal

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun GuessTheAnimalRoot(viewModel: GuessTheAnimalViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    Scaffold { innerPaddings ->
        GuessTheAnimalScreen(state, viewModel::onAction, Modifier.padding(innerPaddings))
    }
}

