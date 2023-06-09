package com.example.clastic.ui.screen.authentication.register

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.clastic.R
import com.example.clastic.ui.screen.ViewModelFactory
import com.example.clastic.ui.screen.authentication.components.AuthenticationButton
import com.example.clastic.ui.screen.authentication.components.AuthenticationMethodDivider
import com.example.clastic.ui.screen.authentication.components.EmailTextField
import com.example.clastic.ui.screen.authentication.components.GoogleSignInButton
import com.example.clastic.ui.screen.authentication.components.NameTextField
import com.example.clastic.ui.screen.authentication.components.PasswordTextField
import com.example.clastic.ui.theme.ClasticTheme
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterScreen(
    navigateToHome: () -> Unit,
    navigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    val mainScope = MainScope()
    val context = LocalContext.current
    val keyboard = LocalSoftwareKeyboardController.current
    val viewModel: RegisterViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            LocalContext.current
        )
    )
    val isEnabled by viewModel.isEnabled.collectAsState()
    val state by viewModel.state.collectAsState()

    var emailInput by remember { mutableStateOf("") }
    var passInput by remember { mutableStateOf("") }
    var nameInput by remember { mutableStateOf("") }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == ComponentActivity.RESULT_OK) {
                mainScope.launch {
                    viewModel.registerWithIntent(
                        intent = result.data ?: return@launch,
                        context = context
                    )
                }
            }
        }
    )

    LaunchedEffect(key1 = state.isAuthSuccessful) {
        if (state.isAuthSuccessful) {
            Toast.makeText(
                context,
                "Register Success",
                Toast.LENGTH_LONG
            ).show()
            viewModel.resetState()
            navigateToHome()
        }
    }

    LaunchedEffect(key1 = state.authError) {
        state.authError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

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
                .padding(horizontal = 48.dp)
                .fillMaxWidth()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            GoogleSignInButton(
                onClick = {
                    mainScope.launch {
                        val loginIntentSender = viewModel.login(context)
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                loginIntentSender ?: return@launch
                            ).build()
                        )
                    }
                },
                stringId = R.string.register_with_google,
                isEnabled = isEnabled,
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth()
            )
            AuthenticationMethodDivider()
            NameTextField(
                name = nameInput,
                isEnabled = isEnabled,
                onInputChanged = { newValue ->
                    nameInput = newValue
                },
                modifier = modifier
                    .fillMaxWidth()
            )
            EmailTextField(
                email = emailInput,
                icon = Icons.Default.Email,
                onInputChanged = { newValue ->
                    emailInput = newValue
                },
                isEnabled = isEnabled,
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
                isEnabled = isEnabled,
                placeholderId = R.string.enter_your_password,
                modifier = modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth()
            )
            AuthenticationButton(
                stringId = R.string.register,
                onClick = {
                    keyboard?.hide()
                    mainScope.launch {
                        viewModel.registerEmailPass(nameInput, emailInput, passInput)
                    }
                },
                isEnabled = isEnabled,
                modifier = modifier
                    .padding(bottom = 24.dp)
            )
            Text(
                text = stringResource(R.string.already_have_account),
                color = Color.Black,
                modifier = modifier
                    .padding(bottom = 8.dp)
            )
            Text(
                text = AnnotatedString(stringResource(R.string.go_to_login)),
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
                color = colorResource(R.color.cyan_textfield),
                modifier = modifier.
                clickable { navigateToLogin() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    ClasticTheme {
        RegisterScreen(
            navigateToLogin = {},
            navigateToHome = {}
        )
    }
}