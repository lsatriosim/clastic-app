package com.example.clastic.ui.screen.transaction.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clastic.R
import com.example.clastic.ui.theme.ClasticTheme

@Composable
fun TransactionTextField(
    text: String,
    labelId: Int,
    modifier: Modifier = Modifier
) {
    val disabledColor = Color.Gray
    Column(modifier = modifier) {
        Text(
            text = stringResource(labelId),
            color = colorResource(R.color.cyan_textfield),
            fontSize = 16.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier
                .padding(start = 10.dp)
        )
        OutlinedTextField(
            value = text,
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = disabledColor,
                unfocusedBorderColor = disabledColor,
            ),
            textStyle = TextStyle(
                color = disabledColor,
                fontSize = 20.sp
            ),
            onValueChange = {},
            maxLines = 4,
            modifier = Modifier
                .fillMaxWidth(),
            readOnly = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionTextFieldPreview() {
    ClasticTheme {
        TransactionTextField(
            text = "aucuaiucbaiubaui",
            labelId = R.string.user_id
        )
    }
}