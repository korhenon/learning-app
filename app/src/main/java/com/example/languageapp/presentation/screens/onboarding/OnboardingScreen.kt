package com.example.languageapp.presentation.screens.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.languageapp.presentation.composables.PrimaryButton
import com.example.languageapp.presentation.theme.LanguageAppTheme
import com.example.languageapp.presentation.theme.fredoka
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    state: OnboardingState,
    onAction: (OnboardingAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val currentPage = state.pages[state.page]
    val coroutineScope = rememberCoroutineScope()
    var isVisible by remember { mutableStateOf(true) }
    AnimatedVisibility(visible = isVisible, enter = fadeIn(), exit = fadeOut()) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(currentPage.image),
                contentDescription = null,
                modifier = Modifier.weight(1f)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(
                                color = if (state.page == it) colorScheme.secondary else colorScheme.surfaceContainerHigh,
                                CircleShape
                            )
                    )
                }
            }
            Spacer(Modifier.height(40.dp))
            Text(
                text = currentPage.title,
                fontSize = 22.sp,
                lineHeight = 28.sp,
                color = colorScheme.onBackground,
                fontWeight = FontWeight.Medium,
                fontFamily = fredoka,
                modifier = Modifier.padding(horizontal = 56.dp),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = currentPage.text,
                fontSize = 15.sp,
                lineHeight = 20.sp,
                color = colorScheme.onSurface,
                fontFamily = fredoka,
                modifier = Modifier.padding(horizontal = 56.dp),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(50.dp))
            PrimaryButton(text = currentPage.buttonText, onClick = {
                coroutineScope.launch {
                    isVisible = false
                    delay(500)
                    onAction(OnboardingAction.Next)
                    isVisible = true
                }

            }, modifier = Modifier.padding(horizontal = 24.dp))
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Skip onboarding",
                fontFamily = fredoka,
                fontSize = 15.sp,
                lineHeight = 20.sp,
                modifier = Modifier.clickable { onAction(OnboardingAction.Skip) }
            )
            Spacer(Modifier.height(64.dp))
        }
    }

}

@Preview
@Composable
private fun OnboardingScreenPreview() {
    LanguageAppTheme {
        Surface {
            OnboardingScreen(state = OnboardingState(), onAction = {})
        }
    }
}