package com.example.languageapp.presentation.screens.guessTheAnimal

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.languageapp.presentation.composables.CustomTextField
import com.example.languageapp.presentation.composables.InternetStateView
import com.example.languageapp.presentation.composables.PrimaryButton
import com.example.languageapp.presentation.composables.QuestionHeader
import com.example.languageapp.presentation.screens.guessTheAnimalResult.GuessTheAnimalResultState
import com.example.languageapp.presentation.theme.LanguageAppTheme
import com.example.languageapp.presentation.utils.InternetState

@Composable
fun GuessTheAnimalScreen(
    state: GuessTheAnimalState,
    onAction: (GuessTheAnimalAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        QuestionHeader(
            title = "Guess the animal",
            onBackClick = { onAction(GuessTheAnimalAction.Back) },
            modifier = modifier
        )
        Spacer(Modifier.height(17.dp))
        Image(
            painter = rememberAsyncImagePainter(state.question.image),
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(20.dp))
        )
        Spacer(Modifier.height(17.dp))
        CustomTextField(
            value = state.answer,
            onValueChange = { onAction(GuessTheAnimalAction.ChangeAnswer(it)) },
            label = "Write who is on image",
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Spacer(Modifier.height(17.dp))
        PrimaryButton(
            text = "Check",
            onClick = { onAction(GuessTheAnimalAction.Check) },
            modifier = Modifier.padding(horizontal = 24.dp)
        )
    }
    InternetStateView(state.internetState, modifier)
}

@Preview
@Composable
private fun GuessTheAnimalScreenPreview() {
    LanguageAppTheme {
        Scaffold { innerPaddings ->
            GuessTheAnimalScreen(
                GuessTheAnimalState(),
                {},
                Modifier.padding(innerPaddings)
            )
        }
    }
}