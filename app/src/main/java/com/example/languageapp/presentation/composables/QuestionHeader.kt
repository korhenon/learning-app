package com.example.languageapp.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.languageapp.R
import com.example.languageapp.presentation.theme.fredoka

@Composable
fun QuestionHeader(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = colorScheme.inversePrimary
) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(color)
            .then(modifier)
            .padding(top = 12.dp, bottom = 20.dp, start = 24.dp, end = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_arrow_back_wide),
            contentDescription = null,
            modifier = Modifier.clickable {
                onBackClick()
            })
        Spacer(Modifier.width(20.dp))
        Text(
            text = title,
            fontFamily = fredoka,
            color = colorScheme.onPrimary,
            fontWeight = FontWeight.Medium,
            fontSize = 22.sp,
            lineHeight = 28.sp
        )
    }
}