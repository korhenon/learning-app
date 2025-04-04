package com.example.languageapp.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.languageapp.R

@OptIn(ExperimentalTextApi::class)
val fredoka = FontFamily(
    Font(
        R.font.fredoka,
        FontWeight.Normal,
        variationSettings = FontVariation.Settings(FontVariation.weight(FontWeight.Normal.weight))
    ),
    Font(
        R.font.fredoka,
        FontWeight.Medium,
        variationSettings = FontVariation.Settings(FontVariation.weight(FontWeight.Medium.weight))
    ),
    Font(
        R.font.fredoka,
        FontWeight.SemiBold,
        variationSettings = FontVariation.Settings(FontVariation.weight(FontWeight.SemiBold.weight))
    ),
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)