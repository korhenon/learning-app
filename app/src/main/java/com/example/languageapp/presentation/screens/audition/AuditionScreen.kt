package com.example.languageapp.presentation.screens.audition

import android.Manifest
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.languageapp.R
import com.example.languageapp.data.models.Word
import com.example.languageapp.presentation.composables.InternetStateView
import com.example.languageapp.presentation.composables.PrimaryButton
import com.example.languageapp.presentation.composables.QuestionHeader
import com.example.languageapp.presentation.theme.LanguageAppTheme
import com.example.languageapp.presentation.theme.fredoka
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AuditionScreen(
    state: AuditionState,
    onAction: (AuditionAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val permissionState = rememberPermissionState(Manifest.permission.RECORD_AUDIO)
    val checkSpeechWithPermissions = {
        if (permissionState.status is PermissionStatus.Denied) {
            permissionState.launchPermissionRequest()
        }
        if (permissionState.status == PermissionStatus.Granted) {
            onAction(AuditionAction.CheckSpeech)
        } else {
            onAction(AuditionAction.NoPermissions)
        }

    }
    Column(Modifier.fillMaxWidth()) {
        QuestionHeader(
            title = "Listening",
            onBackClick = { onAction(AuditionAction.Back) },
            modifier = modifier
        )
        Spacer(Modifier.height(30.dp))
        Text(
            text = state.question.word,
            fontFamily = fredoka,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            color = colorScheme.onBackground,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = state.question.transcription,
            fontFamily = fredoka,
            fontSize = 15.sp,
            lineHeight = 20.sp,
            color = colorScheme.onBackground,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(68.dp))
        Text(
            text = "Please press button and say this word. Our service will check your pronunciation",
            fontFamily = fredoka,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            color = colorScheme.onBackground,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )

        if (state.recognitionResults.isNotEmpty()) {
            Spacer(Modifier.height(5.dp))
            Text(
                text = "Your result",
                fontSize = 15.sp,
                lineHeight = 20.sp,
                fontFamily = fredoka,
                color = colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .background(colorScheme.surface, RoundedCornerShape(16.dp))
                    .padding(horizontal = 20.dp, vertical = 18.dp)
            ) {
                Text(
                    text = state.recognitionResults.last(),
                    fontFamily = fredoka,
                    fontSize = 15.sp,
                    lineHeight = 20.sp,
                    color = if (state.isRight) colorScheme.tertiary else Color(0xFFEF5DA8),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            if (state.isRight) {
                Spacer(Modifier.height(12.dp))
                PrimaryButton(
                    text = "Yay! Go next",
                    onClick = { onAction(AuditionAction.Next) },
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }
        } else {
            Spacer(Modifier.height(8.dp))
            PrimaryButton(
                text = "Check my speech",
                onClick = {
                    checkSpeechWithPermissions()
                },
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
    }
    if (state.recognitionResults.isNotEmpty() && !state.isRight) {
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 53.dp)
        ) {
            Box(
                Modifier
                    .background(Color(0xFF00B5AE), RoundedCornerShape(50.dp))
                    .padding(30.dp)
                    .clickable { checkSpeechWithPermissions() }
            ) {
                Image(
                    painter = painterResource(R.drawable.microphone),
                    contentDescription = null,
                    Modifier.size(100.dp)
                )
            }
        }
    }
    InternetStateView(state.internetState, modifier)
}

@Preview
@Composable
private fun AuditionScreenPreview() {
    LanguageAppTheme {
        Scaffold { innerPaddings ->
            AuditionScreen(
                AuditionState(
                    Word("gladiolus", "[ɡlædɪ'əʊləs]", "Гладиолус"),
                    listOf("pupumber"),
                    false
                ), {}, Modifier.padding(innerPaddings)
            )
        }
    }
}