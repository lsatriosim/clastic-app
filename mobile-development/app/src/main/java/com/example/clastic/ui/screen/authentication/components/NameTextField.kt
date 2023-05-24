package com.example.clastic.ui.screen.authentication.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.clastic.R
import com.example.clastic.ui.theme.ClasticTheme

@Composable
fun NameTextField(
    name: String,
    onInputChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.name),
            color = colorResource(R.color.cyan_textfield),
            fontWeight = FontWeight.Black,
            modifier = Modifier
                .padding(start = 10.dp)
        )
        OutlinedTextField(
            value = name,
            onValueChange = onInputChanged,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = Color.Black
                )
            },
            shape = RoundedCornerShape(19.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(R.color.cyan_textfield),
                unfocusedBorderColor = colorResource(R.color.cyan_textfield),
            ),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words
            ),
            placeholder = {
                Text(
                    text = stringResource(R.string.enter_name),
                    color = Color.Gray
                )
            },
            modifier = modifier
                .padding(bottom = 12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NameTextFieldPreview() {
    ClasticTheme {
        NameTextField(
            name = "",
            onInputChanged = {}
        )
    }
}
