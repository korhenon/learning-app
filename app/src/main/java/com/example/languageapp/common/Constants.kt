package com.example.languageapp.common

import com.example.languageapp.R
import com.example.languageapp.data.models.OnboardingPage

val OnboardingPages = listOf(
    OnboardingPage(
        title = "Confidence in your words",
        text = "With conversation-based learning, you'll be talking from lesson one",
        buttonText = "Next",
        image = R.drawable.onboard_1
    ),
    OnboardingPage(
        title = "Take your time to learn",
        text = "Develop a habit of learning and make it a part of your daily routine",
        buttonText = "More",
        image = R.drawable.onboard_2
    ),
    OnboardingPage(
        title = "The lessons you need to learn",
        text = "Using a variety of learning styles to learn and retain",
        buttonText = "Choose a language",
        image = R.drawable.onboard_3
    ),
)

val Languages = listOf("English", "Russian", "Belarus", "Kazakh", "Chinese")

const val BASE_URL = "https://congenial-spoon.onrender.com/"