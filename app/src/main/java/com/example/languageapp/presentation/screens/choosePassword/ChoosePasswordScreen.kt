package com.example.languageapp.presentation.screens.choosePassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import com.example.languageapp.presentation.composables.CustomTextField
import com.example.languageapp.presentation.composables.InternetStateView
import com.example.languageapp.presentation.composables.PrimaryButton
import com.example.languageapp.presentation.theme.LanguageAppTheme
import com.example.languageapp.presentation.theme.fredoka

@Composable
fun ChoosePasswordScreen(
    state: ChoosePasswordState,
    onAction: (ChoosePasswordAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .background(colorScheme.inversePrimary)
                .then(modifier)
                .padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_arrow_back),
                contentDescription = null,
                modifier = Modifier.clickable { onAction(ChoosePasswordAction.Back) })
            Text(
                text = "Signup",
                modifier = Modifier
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
            text = "Choose a Password",
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
            value = state.password,
            onValueChange = { onAction(ChoosePasswordAction.ChangePassword(it)) },
            label = "Password",
            modifier = Modifier.padding(horizontal = 24.dp),
            isPassword = true,
            isError = !state.isPasswordValid
        )
        Spacer(Modifier.height(24.dp))
        CustomTextField(
            value = state.confirmPassword,
            onValueChange = { onAction(ChoosePasswordAction.ChangeConfirmPassword(it)) },
            label = "Confirm Password",
            modifier = Modifier.padding(horizontal = 24.dp),
            isPassword = true,
        )
        Spacer(Modifier.height(25.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 24.dp)
        ) {
            Checkbox(
                checked = state.acceptPrivacy,
                onCheckedChange = { onAction(ChoosePasswordAction.ChangePrivacy(it)) },
                colors = CheckboxDefaults.colors(
                    uncheckedColor = colorScheme.primary
                ),
            )
            Text(
                text = buildAnnotatedString {
                    append("I ")
                    withStyle(
                        SpanStyle(
                            color = colorScheme.primary,
                            fontWeight = FontWeight.Medium
                        )
                    ) {
                        append("have made myself acquainted with")
                    }
                },
                textAlign = TextAlign.Center,
                fontSize = 17.sp,
                lineHeight = 22.sp,
                fontFamily = fredoka,
                color = colorScheme.inverseOnSurface,
                modifier = Modifier.clickable { onAction(ChoosePasswordAction.OpenPrivacy) }
            )
        }
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(color = colorScheme.primary, fontWeight = FontWeight.Medium)) {
                    append("the Rules")
                }
                append("_and accept all its provisions,")
            },
            textAlign = TextAlign.Center,
            fontSize = 17.sp,
            lineHeight = 22.sp,
            fontFamily = fredoka,
            color = colorScheme.inverseOnSurface,
            modifier = Modifier
                .clickable { onAction(ChoosePasswordAction.OpenPrivacy) }
                .padding(start = 24.dp)
        )
        Spacer(Modifier.height(73.dp))
        PrimaryButton(
            text = "Signup",
            onClick = { onAction(ChoosePasswordAction.Signup) },
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
                .clickable { onAction(ChoosePasswordAction.Login) },
            textAlign = TextAlign.Center,
            fontSize = 17.sp,
            lineHeight = 22.sp,
            fontFamily = fredoka,
            color = colorScheme.inverseOnSurface
        )
    }
    InternetStateView(state.internetState, modifier)
}

@Preview
@Composable
private fun ChoosePasswordScreenPreview() {
    LanguageAppTheme {
        Scaffold { innerPadding ->
            ChoosePasswordScreen(ChoosePasswordState(), {}, Modifier.padding(innerPadding))
        }
    }
}