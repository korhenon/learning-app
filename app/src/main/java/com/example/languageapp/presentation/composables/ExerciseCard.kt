package com.example.languageapp.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.languageapp.presentation.theme.fredoka

@Composable
fun ExerciseCard(
    image: Int,
    name: String,
    onClick: () -> Unit,
    color: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(color, RoundedCornerShape(20.dp))
            .padding(11.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(image), contentDescription = null, Modifier.size(70.dp))
        Text(
            text = name,
            fontSize = 13.sp,
            lineHeight = 18.sp,
            color = colorScheme.onPrimary,
            fontFamily = fredoka
        )
    }
}