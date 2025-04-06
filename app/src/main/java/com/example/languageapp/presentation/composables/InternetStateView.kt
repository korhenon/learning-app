package com.example.languageapp.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StampedPathEffectStyle
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.languageapp.R
import com.example.languageapp.presentation.theme.fredoka
import com.example.languageapp.presentation.utils.InternetState

@Composable
fun InternetStateView(state: InternetState, modifier: Modifier = Modifier) {
    if (state.isLoading) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color(0x4D606060)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (state.isNoInternet) {
        Column(Modifier
            .fillMaxWidth()
            .background(colorScheme.background)) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(colorScheme.inversePrimary)
                    .then(modifier)
            ) {
                Spacer(Modifier.height(48.dp))
            }
            Spacer(Modifier.height(111.dp))
            Image(
                painter = painterResource(R.drawable.no_internet),
                contentDescription = null,
                Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
            )
            Spacer(Modifier.height(14.dp))
            Text(
                text = "No \ninternet connection",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = fredoka,
                fontWeight = FontWeight.Medium,
                fontSize = 30.sp,
                lineHeight = 28.sp,
                color = colorScheme.onBackground
            )
            Spacer(Modifier.weight(1f))
            PrimaryButton(
                text = "Check again",
                onClick = {},
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(Modifier.height(30.dp))

        }
    }
}