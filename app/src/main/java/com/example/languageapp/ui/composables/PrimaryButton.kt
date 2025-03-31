package com.example.languageapp.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.languageapp.R
import com.example.languageapp.ui.theme.LanguageAppTheme
import com.example.languageapp.ui.theme.fredoka

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    var buttonModifier = modifier.fillMaxWidth()
    if (enabled) {
        buttonModifier = buttonModifier.clickable { onClick() }
    }
    Box(modifier = buttonModifier, contentAlignment = Alignment.Center)
    {
        Image(
            painter = painterResource(R.drawable.button_background),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            colorFilter = if (!enabled) ColorFilter.tint(colorScheme.surfaceContainerLow) else null
        )
        Text(
            text = text,
            fontSize = 20.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = fredoka,
            color = colorScheme.onPrimary
        )
    }
}

@Preview
@Composable
private fun PrimaryButtonPreview() {
    LanguageAppTheme {
        PrimaryButton(text = "Hello", onClick = {}, enabled = true)
    }
}