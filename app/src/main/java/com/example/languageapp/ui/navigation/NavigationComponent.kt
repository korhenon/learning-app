package com.example.languageapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.languageapp.ui.screens.onboarding.OnboardingRoot
import com.example.languageapp.ui.screens.selectLanguage.SelectLanguageRoot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun NavigationComponent(navigator: Navigator) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val navController = rememberNavController()

    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                navigator.navigationActions.collect { action ->
                    when (action) {
                        is NavigationAction.Navigate -> {
                            navController.navigate(action.destination) {
                                action.navOptions(this)
                            }
                        }
                        NavigationAction.PopBackStack -> {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }

    NavHost(navController = navController, startDestination = navigator.startDestination) {
        composable<Destination.Onboarding> { OnboardingRoot() }
        composable<Destination.SelectLanguage> { SelectLanguageRoot() }
    }
}