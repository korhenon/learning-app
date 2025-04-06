package com.example.languageapp.presentation.screens.wordPractice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.languageapp.data.models.Word
import com.example.languageapp.presentation.composables.Answer
import com.example.languageapp.presentation.composables.InternetStateView
import com.example.languageapp.presentation.composables.PrimaryButton
import com.example.languageapp.presentation.composables.QuestionHeader
import com.example.languageapp.presentation.theme.LanguageAppTheme
import com.example.languageapp.presentation.theme.fredoka

@Composable
fun WordPracticeScreen(
    state: WordPracticeState,
    onAction: (WordPracticeAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(Modifier.fillMaxWidth()) {
        QuestionHeader(
            title = "Word practice",
            onBackClick = { onAction(WordPracticeAction.Back) },
            modifier = modifier
        )
        Spacer(Modifier.height(34.dp))
        Text(
            text = state.question.word,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.SemiBold,
            fontFamily = fredoka,
            fontSize = 28.sp,
            lineHeight = 34.sp,
            color = colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(2.dp))
        Text(
            text = state.question.transcription,
            modifier = Modifier.fillMaxWidth(),
            fontFamily = fredoka,
            fontSize = 17.sp,
            lineHeight = 22.sp,
            color = colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(55.dp))
        Column(
            modifier = Modifier.padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(11.dp)
        ) {
            for (answer in state.answers) {
                println(answer == state.question)
                Answer(
                    text = answer.translation,
                    isSelected = state.selected == answer,
                    isChecked = state.isChecked,
                    isRight = answer == state.question,
                    onSelect = {
                        onAction(WordPracticeAction.SelectWord(answer))
                    }
                )
            }
        }
    }
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        PrimaryButton(
            text = if (state.isChecked) "Next" else "Check",
            onClick = {
                if (state.isChecked) onAction(WordPracticeAction.Next)
                else onAction(WordPracticeAction.Check)
            },
            modifier = Modifier.padding(24.dp)
        )
    }
    InternetStateView(state.internetState, modifier)
}

@Preview
@Composable
private fun WordPracticeScreenPreview() {
    LanguageAppTheme {
        Scaffold { innerPaddings ->
            WordPracticeScreen(
                WordPracticeState(
                    question = Word("gardener", "['gɑ:dnə]", "Садовник"),
                    answers = listOf(
                        Word("fly", "[flaɪ]", "Муха"),
                        Word("gardener", "['gɑ:dnə]", "Садовник"),
                        Word("gladiolus", "[ɡlædɪ'əʊləs]", "Гладиолус"),
                        Word("dog", "[dɒɡ]", "Собака")
                    ),
                    selected = Word("fly", "[flaɪ]", "Муха"),
                    isChecked = true
                ), {}, Modifier.padding(innerPaddings)
            )
        }
    }
}
