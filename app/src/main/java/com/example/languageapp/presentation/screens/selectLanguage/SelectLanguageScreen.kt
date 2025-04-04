package com.example.languageapp.presentation.screens.selectLanguage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.languageapp.presentation.composables.PrimaryButton
import com.example.languageapp.presentation.theme.LanguageAppTheme
import com.example.languageapp.presentation.theme.fredoka

@Composable
fun SelectLanguageScreen(
    state: SelectLanguageState,
    onAction: (SelectLanguageAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(Modifier.fillMaxWidth()) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(colorScheme.inversePrimary)
                .padding(start = 24.dp, end = 64.dp, top = 16.dp, bottom = 20.dp)
        ) {
            Text(
                text = "Language select",
                modifier = modifier.fillMaxWidth(),
                fontSize = 17.sp,
                lineHeight = 22.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = fredoka,
                color = colorScheme.onPrimary,
                textAlign = TextAlign.Center
            )
        }
        Spacer(Modifier.height(12.dp))
        Text(
            text = "What is your Mother language?",
            modifier = Modifier.padding(start = 24.dp),
            fontFamily = fredoka,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight.Medium,
            color = colorScheme.onBackground
        )
        Spacer(Modifier.height(16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(state.languages) {
                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .background(
                            if (state.selectedLanguage == it) colorScheme.secondary else colorScheme.secondaryContainer,
                            RoundedCornerShape(20.dp)
                        )
                        .padding(horizontal = 15.dp, vertical = 20.dp),
                    fontSize = 22.sp,
                    lineHeight = 28.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = fredoka
                )
            }
        }
    }
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        PrimaryButton(text = "Choose", onClick = {
            onAction(SelectLanguageAction.Choose)
        }, modifier = Modifier.padding(24.dp))
    }
}

@Preview
@Composable
private fun SelectLanguageScreenPreview() {
    LanguageAppTheme {
        Surface {
            SelectLanguageScreen(SelectLanguageState(), {})
        }
    }
}