package com.example.languageapp.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.languageapp.R
import com.example.languageapp.data.models.ExerciseType
import com.example.languageapp.data.models.User
import com.example.languageapp.presentation.composables.ExerciseCard
import com.example.languageapp.presentation.theme.LanguageAppTheme
import com.example.languageapp.presentation.theme.fredoka

@Composable
fun HomeScreen(state: HomeState, onAction: (HomeAction) -> Unit, modifier: Modifier = Modifier) {
    Column {
        Column(
            Modifier
                .fillMaxWidth()
                .background(colorScheme.inversePrimary)
                .then(modifier)
                .padding(start = 24.dp, end = 24.dp, top = 6.dp, bottom = 12.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(state.currentUser.photo),
                contentDescription = null,
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = "Hello, ${state.currentUser.name}",
                fontSize = 22.sp,
                lineHeight = 28.sp,
                fontFamily = fredoka,
                color = colorScheme.onPrimary,
                fontWeight = FontWeight.Medium
            )
            Spacer(Modifier.height(5.dp))
            Text(
                text = "Are you ready for learning today?",
                fontFamily = fredoka,
                color = Color(0xFFB6B6B6),
                fontWeight = FontWeight.Medium,
                fontSize = 17.sp,
                lineHeight = 22.sp
            )
        }
        Spacer(Modifier.height(12.dp))
        Text(
            text = "Top users",
            fontSize = 20.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Medium,
            color = colorScheme.onBackground,
            fontFamily = fredoka,
            modifier = Modifier.padding(start = 24.dp)
        )
        Spacer(Modifier.height(5.dp))
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            for (user in state.topUsers) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .background(Color(0xFFE5E5E5), RoundedCornerShape(20.dp))
                        .padding(top = 20.dp, bottom = 20.dp, start = 16.dp, end = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(user.photo),
                        contentDescription = null,
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                    )
                    Spacer(Modifier.width(24.dp))
                    Text(
                        text = user.name,
                        modifier = Modifier.weight(1f),
                        fontSize = 17.sp,
                        fontFamily = fredoka,
                        fontWeight = FontWeight.Medium,
                        color = colorScheme.onBackground,
                        lineHeight = 22.sp
                    )

                    Text(
                        text = "${user.points.toInt()} points",
                        fontSize = 17.sp,
                        fontFamily = fredoka,
                        fontWeight = FontWeight.Medium,
                        color = colorScheme.onBackground,
                        lineHeight = 22.sp
                    )
                }
            }
        }
        Spacer(Modifier.height(11.dp))
        Text(
            text = "Available excersises",
            fontSize = 20.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Medium,
            color = colorScheme.onBackground,
            fontFamily = fredoka,
            modifier = Modifier.padding(start = 24.dp)
        )
        Spacer(Modifier.height(9.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp), horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            ExerciseCard(
                image = R.drawable.guess_the_animal,
                name = "Guess the animal",
                onClick = { onAction(HomeAction.OpenExercise(ExerciseType.GuessTheAnimal)) },
                color = colorScheme.primary,
                modifier = Modifier.weight(1f)
            )
            ExerciseCard(
                image = R.drawable.word_practice,
                name = "Word practice",
                onClick = { onAction(HomeAction.OpenExercise(ExerciseType.WordPractice)) },
                color = colorScheme.error,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(Modifier.height(16.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp), horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            ExerciseCard(
                image = R.drawable.audition,
                name = "Audition",
                onClick = { onAction(HomeAction.OpenExercise(ExerciseType.Audition)) },
                color = colorScheme.secondary,
                modifier = Modifier.weight(1f)
            )
            ExerciseCard(
                image = R.drawable.game,
                name = "Game",
                onClick = {},
                color = colorScheme.tertiary,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    LanguageAppTheme {
        Scaffold { innerPadding ->
            HomeScreen(
                HomeState(
                    currentUser = User(
                        name = "Emil",
                        photo = "https://masterpiecer-images.s3.yandex.net/5fd531dca6427c7:upscaled",
                        0f
                    ),
                    topUsers = listOf(
                        User(
                            name = "Vincent van Gogh",
                            photo = "https://masterpiecer-images.s3.yandex.net/5fd531dca6427c7:upscaled",
                            points = 12f
                        ),
                        User(
                            name = "Dmitri Ivanovich Mendeleev",
                            photo = "https://masterpiecer-images.s3.yandex.net/5fd531dca6427c7:upscaled",
                            points = 10f
                        ),
                        User(
                            name = "Vlad Tepes",
                            photo = "https://masterpiecer-images.s3.yandex.net/5fd531dca6427c7:upscaled",
                            points = 8f
                        )
                    )
                ), {}, Modifier.padding(innerPadding)
            )
        }
    }
}