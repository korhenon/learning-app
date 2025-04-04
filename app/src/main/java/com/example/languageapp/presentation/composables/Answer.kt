package com.example.languageapp.presentation.composables

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
import androidx.compose.ui.unit.sp
import com.example.languageapp.R
import com.example.languageapp.data.models.Word
import com.example.languageapp.presentation.theme.fredoka

@Composable
fun Answer(
    text: String,
    isSelected: Boolean,
    isChecked: Boolean,
    isRight: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    val buttonBackground =
        if (!isSelected && !isRight) R.drawable.button_gray
        else if (!isChecked) R.drawable.button_primary
        else if (isRight) R.drawable.button_tertiary
        else R.drawable.button_secondary

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onSelect() },
        contentAlignment = Alignment.Center
    )
    {
        Image(
            painter = painterResource(buttonBackground),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = text,
            fontSize = 20.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = fredoka,
            color = if (buttonBackground == R.drawable.button_secondary) colorScheme.onPrimary else colorScheme.onBackground
        )
    }
}