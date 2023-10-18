package com.example.friends.signup

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.friends.R

@Composable
@Preview(showBackground = true)
fun SignUp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
    {
        ScreenTitle(titleResource = R.string.create_an_account)
        Spacer(modifier = Modifier.height(16.dp))
        var email by remember { mutableStateOf("") }
        EmailField(
            value = email,
            onValueChanged = { email = it })
        var password by remember { mutableStateOf("") }
        PasswordField(
            value = password,
            onValueChanged = { password = it },
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {}) {
            Text(text = stringResource(id = R.string.signUp))
        }
    }
}

@Composable
private fun ScreenTitle(@StringRes titleResource: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = titleResource),
            style = typography.headlineMedium
        )
    }
}
@Composable
private fun EmailField(
    value: String,
    onValueChanged: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        label = { Text(text = stringResource(id = R.string.email)) },
        onValueChange = onValueChanged
    )
}
@Composable
private fun PasswordField(
    value: String,
    onValueChanged: (String) -> Unit,
) {
    var isVisible by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        trailingIcon = {
            IconButton(onClick = { isVisible = !isVisible }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_visibility_eye_24),
                    contentDescription = null
                )
            }
        },
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
        label = { Text(text = stringResource(id = R.string.password)) },
        onValueChange = onValueChanged
    )
}


