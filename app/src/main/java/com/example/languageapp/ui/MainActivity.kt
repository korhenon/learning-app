package com.example.languageapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.languageapp.ui.navigation.NavigationComponent
import com.example.languageapp.ui.navigation.Navigator
import com.example.languageapp.ui.theme.LanguageAppTheme
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