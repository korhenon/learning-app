package com.example.languageapp.ui.screens.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.languageapp.R
import com.example.languageapp.ui.composables.CustomTextField
import com.example.languageapp.ui.composables.PrimaryButton
import com.example.languageapp.ui.screens.login.LoginAction
import com.example.languageapp.ui.theme.LanguageAppTheme
import com.example.languageapp.ui.theme.fredoka

@Composable
fun SignUpScreen(
    state: SignUpState,
    onAction: (SignUpAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .background(colorScheme.inversePrimary)
                .padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_arrow_back),
                contentDescription = null,
                modifier = Modifier.clickable { onAction(SignUpAction.Back) })
            Text(
                text = "Signup",
                modifier = modifier
                    .weight(1f)
                    .padding(start = 16.dp, end = 40.dp),
                fontSize = 17.sp,
                lineHeight = 22.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = fredoka,
                color = colorScheme.onPrimary,
                textAlign = TextAlign.Center
            )
        }
        Spacer(Modifier.height(40.dp))
        Text(
            text = "Create an Account",
            fontSize = 22.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = fredoka,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 56.dp),
            color = colorScheme.onBackground
        )
        Spacer(Modifier.height(32.dp))
        CustomTextField(
            value = state.firstName,
            onValueChange = { onAction(SignUpAction.ChangeFirstName(it)) },
            label = "First Name",
            placeholder = "Your First Name",
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Spacer(Modifier.height(24.dp))
        CustomTextField(
            value = state.lastName,
            onValueChange = { onAction(SignUpAction.ChangeLastName(it)) },
            label = "Last Name",
            placeholder = "Your Last Name",
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Spacer(Modifier.height(24.dp))
        CustomTextField(
            value = state.email,
            onValueChange = { onAction(SignUpAction.ChangeEmail(it)) },
            label = "Email Address",
            placeholder = "Email",
            modifier = Modifier.padding(horizontal = 24.dp),
            isError = !state.isEmailValid
        )
        Spacer(Modifier.height(34.dp))
        PrimaryButton(
            text = "Continue",
            onClick = { onAction(SignUpAction.Continue) },
            modifier = modifier.padding(horizontal = 24.dp)
        )
        Spacer(Modifier.height(24.dp))
        Text(
            text = buildAnnotatedString {
                append("Already you member? ")
                withStyle(SpanStyle(color = colorScheme.primary, fontWeight = FontWeight.Medium)) {
                    append("Login")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onAction(SignUpAction.Login) },
            textAlign = TextAlign.Center,
            fontSize = 17.sp,
            lineHeight = 22.sp,
            fontFamily = fredoka,
            color = colorScheme.inverseOnSurface
        )
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    LanguageAppTheme {
        Scaffold {
            SignUpScreen(SignUpState(), {}, modifier = Modifier.padding(it))
        }
    }
}