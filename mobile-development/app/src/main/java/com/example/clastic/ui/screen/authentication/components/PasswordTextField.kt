package com.example.clastic.ui.screen.authentication.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.clastic.R
import com.example.clastic.ui.theme.ClasticTheme

@Composable
fun PasswordTextField(
    password: String,
    isEnabled: Boolean,
    placeholderId: Int,
    onInputChanged: (String) -> Unit,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    var passwordVisible by remember { mutableStateOf(false) }

    // TODO(Password Criteria Here)
    //val isEmptyPassword = remember(password) { password.isBlank() }
    //val isValidPassword = remember(password) { password }
    //val isError = isValidPassword && !isEmptyPassword
    val isError = false

    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.password),
            color = if (!isError) colorResource(R.color.cyan_textfield) else Color.Red,
            fontWeight = FontWeight.Black,
            modifier = Modifier
                .padding(start = 10.dp)
        )
        OutlinedTextField(
            value = password,
            onValueChange = onInputChanged,
            enabled = isEnabled,
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.Black
                )
            },
            shape = RoundedCornerShape(19.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(R.color.cyan_textfield),
                unfocusedBorderColor = colorResource(R.color.cyan_textfield),
                errorBorderColor = Color.Red
            ),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.clearFocus() }
            ),
            placeholder = {
                Text(
                    stringResource(placeholderId),
                    color = Color.Gray
                )
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            isError = isError,
            trailingIcon = {
                val visibleIcon = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = stringResource( if (passwordVisible) R.string.show_password else R.string.hide_password)

                IconButton(
                    onClick = { passwordVisible = !passwordVisible },
                ) {
                    Icon(
                        imageVector = visibleIcon,
                        tint = Color.Black,
                        contentDescription = description
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
        )

        if (isError) {
            Text(
                text = stringResource(R.string.invalid_password), // TODO(Explain password criteria here)
                color = Color.Red,
                style = MaterialTheme.typography.caption,
                modifier = modifier.padding(start = 10.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PasswordTextFieldPreview() {
    ClasticTheme {
        PasswordTextField(
            password = "invalid input",
            placeholderId = R.string.enter_a_password,
            onInputChanged = {},
            isEnabled = true,
            icon = Icons.Default.Lock
        )
    }
}