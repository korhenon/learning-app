package com.example.languageapp.ui.screens.login

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginRoot(viewModel: LoginViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    Scaffold { innerPadding ->
        LoginScreen(state, viewModel::onAction, Modifier.padding(innerPadding))
    }
}