package com.example.languageapp.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.languageapp.R
import com.example.languageapp.ui.theme.LanguageAppTheme
import com.example.languageapp.ui.theme.fredoka

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    isPassword: Boolean = false,
    isError: Boolean = false
) {
    var hasVisualTransformation by rememberSaveable { mutableStateOf(true) }
    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 15.sp,
            lineHeight = 20.sp,
            fontFamily = fredoka,
            color = colorScheme.onBackground
        )
        Spacer(Modifier.height(8.dp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                if (isPassword) {
                    Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                        repeat(7) {
                            Box(
                                Modifier
                                    .size(6.dp)
                                    .background(colorScheme.onSurfaceVariant, CircleShape)
                            )
                        }
                    }
                } else {
                    Text(
                        text = placeholder,
                        fontSize = 15.sp,
                        lineHeight = 20.sp,
                        fontFamily = fredoka,
                        color = colorScheme.onSurfaceVariant
                    )
                }
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorScheme.surface,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedTextColor = colorScheme.onBackground,
                focusedContainerColor = colorScheme.surface,
                focusedIndicatorColor = Color.Transparent,
                focusedTextColor = colorScheme.onBackground,
                errorTextColor = colorScheme.error,
                errorIndicatorColor = Color.Transparent,
                errorContainerColor = colorScheme.errorContainer
            ),
            textStyle = TextStyle(
                fontSize = 15.sp,
                lineHeight = 20.sp,
                fontFamily = fredoka,
            ),
            trailingIcon = if (isPassword) {
                {
                    IconButton(onClick = {
                        hasVisualTransformation = !hasVisualTransformation
                    }) {
                        Image(
                            painter = painterResource(R.drawable.ic_eye),
                            contentDescription = null
                        )
                    }
                }
            } else null,
            visualTransformation = if (isPassword && hasVisualTransformation) PasswordVisualTransformation() else VisualTransformation.None,
            isError = isError
        )
    }

}

@Preview
@Composable
private fun CustomTextFieldPreview() {
    Surface {
        LanguageAppTheme {
            Box(Modifier.padding(24.dp)) {
                CustomTextField(
                    value = "fasfsdfs",
                    onValueChange = {},
                    "Email Address",
                    placeholder = "Email",
                    isError = true
                )
            }
        }
    }
}