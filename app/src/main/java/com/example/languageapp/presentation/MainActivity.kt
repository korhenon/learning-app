package com.example.languageapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.languageapp.presentation.navigation.NavigationComponent
import com.example.languageapp.presentation.navigation.Navigator
import com.example.languageapp.presentation.theme.LanguageAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigator: Navigator
    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        var keepSplashVisible = true
        super.onCreate(savedInstanceState)
        splash.setKeepOnScreenCondition { keepSplashVisible }
        lifecycleScope.launch {
            // TODO: Add normal logic for splash screen
            keepSplashVisible = false
        }
        enableEdgeToEdge()
        setContent {
            LanguageAppTheme {
                NavigationComponent(navigator)
            }
        }
    }
}