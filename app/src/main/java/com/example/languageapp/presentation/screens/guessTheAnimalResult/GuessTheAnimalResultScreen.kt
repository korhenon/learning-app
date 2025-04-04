package com.example.languageapp.presentation.screens.guessTheAnimalResult

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.languageapp.R
import com.example.languageapp.presentation.composables.PrimaryButton
import com.example.languageapp.presentation.composables.QuestionHeader
import com.example.languageapp.presentation.screens.guessTheAnimal.GuessTheAnimalScreen
import com.example.languageapp.presentation.theme.LanguageAppTheme
import com.example.languageapp.presentation.theme.fredoka

@Composable
fun GuessTheAnimalResultScreen(
    state: GuessTheAnimalResultState,
    onAction: (GuessTheAnimalResultAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(Modifier.fillMaxWidth()) {
        QuestionHeader(
            title = "Guess the animal",
            onBackClick = { onAction(GuessTheAnimalResultAction.Back) },
            color = if (state.isRight) colorScheme.tertiary else colorScheme.error,
            modifier = modifier
        )
        Spacer(Modifier.height(63.dp))
        Image(
            painter = painterResource(if (state.isRight) R.drawable.guess_animal_success else R.drawable.guess_animal_failed),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(45.dp))
        Text(
            text = if (state.isRight) "Holy Molly! That is Right!\n" else "Eh? Wrong answer :(\nThat is: ${state.rightAnswer}",
            fontFamily = fredoka,
            fontSize = 20.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(18.dp))
        PrimaryButton(
            text = "Next",
            onClick = { onAction(GuessTheAnimalResultAction.Next) },
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        if (!state.isRight) {
            Spacer(Modifier.height(11.dp))
            PrimaryButton(
                text = "Try again",
                onClick = { onAction(GuessTheAnimalResultAction.TryAgain) },
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
    }
}

@Preview
@Composable
private fun GuessTheAnimalResultScreenPreview() {
    LanguageAppTheme {
        Scaffold { innerPaddings ->
            GuessTheAnimalResultScreen(
                GuessTheAnimalResultState(isRight = false, rightAnswer = "Racoon"),
                onAction = {},
                modifier = Modifier.padding(innerPaddings)
            )
        }
    }
}