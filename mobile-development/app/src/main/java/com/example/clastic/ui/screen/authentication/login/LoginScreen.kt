package com.example.clastic.ui.screen.authentication.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.clastic.R
import com.example.clastic.ui.screen.authentication.components.AuthenticationButton
import com.example.clastic.ui.screen.authentication.components.AuthenticationMethodDivider
import com.example.clastic.ui.screen.authentication.components.EmailTextField
import com.example.clastic.ui.screen.authentication.components.GoogleSignInButton
import com.example.clastic.ui.screen.authentication.components.PasswordTextField
import com.example.clastic.ui.theme.ClasticTheme

@Composable
fun LoginScreen(
    navigateToRegister: () -> Unit,
    modifier: Modifier = Modifier
) {
    var emailInput by remember { mutableStateOf("") }
    var passInput by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.clastic_logo_text),
            contentDescription = null,
            modifier = modifier
                .padding(bottom = 12.dp)
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            GoogleSignInButton(
                onClick = { TODO() },
                stringId = R.string.sign_in_with_google,
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth()
            )
            AuthenticationMethodDivider()
            EmailTextField(
                icon = Icons.Default.Email,
                onInputChanged = { newValue ->
                    emailInput = newValue
                },
                email = emailInput,
                modifier = modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth()
            )
            PasswordTextField(
                password = passInput,
                icon = Icons.Default.Lock,
                onInputChanged = { newValue ->
                    passInput = newValue
                },
                placeholderId = R.string.enter_a_password,
                modifier = modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth()
            )
            AuthenticationButton(
                stringId = R.string.login,
                onClick = { TODO() },
                modifier = modifier
                    .padding(bottom = 24.dp)
            )
            Text(
                text = stringResource(R.string.dont_have_account),
                color = Color.Black,
                modifier = modifier
                    .padding(bottom = 8.dp)
            )
            Text(
                text = AnnotatedString(stringResource(R.string.go_to_register)),
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
                color = colorResource(R.color.cyan_textfield),
                modifier = modifier.
                clickable { navigateToRegister() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    ClasticTheme {
        LoginScreen(
            navigateToRegister = {}
        )
    }
}