package com.example.languageapp.presentation.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.languageapp.presentation.composables.PrimaryButton
import com.example.languageapp.presentation.theme.fredoka

@Composable
fun ProfileScreen(
    state: ProfileState,
    onAction: (ProfileAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(colorScheme.inversePrimary)
            .then(modifier)
            .padding(start = 24.dp, end = 24.dp, top = 6.dp, bottom = 20.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(state.currentUser.photo),
            contentDescription = null,
            modifier = Modifier
                .size(134.dp)
                .clip(CircleShape)
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = "Your profile, ${state.currentUser.name}",
            fontSize = 22.sp,
            lineHeight = 28.sp,
            fontFamily = fredoka,
            color = colorScheme.onPrimary,
            fontWeight = FontWeight.Medium
        )
    }
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            PrimaryButton(
                text = "Switch to Dark",
                onClick = { onAction(ProfileAction.SwitchToDark) }
            )
            Spacer(Modifier.height(10.dp))
            PrimaryButton(
                text = "Change mother language",
                onClick = { onAction(ProfileAction.ChangeLanguage) }
            )
            Spacer(Modifier.height(10.dp))
            PrimaryButton(
                text = "Change your image",
                onClick = { onAction(ProfileAction.ChangeImage) }
            )
            Spacer(Modifier.height(14.dp))
            PrimaryButton(
                text = "Logout",
                onClick = { onAction(ProfileAction.Logout) },
                isGray = true
            )
        }
    }
}
