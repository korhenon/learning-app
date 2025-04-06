package com.example.languageapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.languageapp.domain.navigation.Destination
import com.example.languageapp.domain.navigation.NavigationAction
import com.example.languageapp.domain.navigation.Navigator
import com.example.languageapp.presentation.screens.audition.AuditionRoot
import com.example.languageapp.presentation.screens.choosePassword.ChoosePasswordRoot
import com.example.languageapp.presentation.screens.guessTheAnimal.GuessTheAnimalRoot
import com.example.languageapp.presentation.screens.guessTheAnimalResult.GuessTheAnimalResultRoot
import com.example.languageapp.presentation.screens.home.HomeRoot
import com.example.languageapp.presentation.screens.login.LoginRoot
import com.example.languageapp.presentation.screens.onboarding.OnboardingRoot
import com.example.languageapp.presentation.screens.profile.ProfileRoot
import com.example.languageapp.presentation.screens.selectLanguage.SelectLanguageRoot
import com.example.languageapp.presentation.screens.signup.SignUpRoot
import com.example.languageapp.presentation.screens.wordPractice.WordPracticeRoot
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
        composable<Destination.Login> { LoginRoot() }
        composable<Destination.Signup> { SignUpRoot() }
        composable<Destination.ChoosePassword> { ChoosePasswordRoot() }
        composable<Destination.Home> { HomeRoot() }
        composable<Destination.GuessTheAnimal> { GuessTheAnimalRoot() }
        composable<Destination.GuessTheAnimalResult> { GuessTheAnimalResultRoot() }
        composable<Destination.WordPractice> { WordPracticeRoot() }
        composable<Destination.Audition> { AuditionRoot() }
        composable<Destination.Profile> { ProfileRoot() }
    }
}