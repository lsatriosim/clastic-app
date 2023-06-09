package com.example.clastic.ui.screen.transaction.components

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.clastic.R
import com.example.clastic.ui.theme.ClasticTheme

@Composable
fun PlasticWeightTextField(
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
){
    val cyanColor = colorResource(R.color.cyan_textfield)
    val blackColor = Color.Black
    val grayColor = Color.Gray
    var plasticWeight by rememberSaveable {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = plasticWeight,
        onValueChange = { newWeight ->
            if (newWeight.length <= 3) {
                plasticWeight = newWeight
                onValueChange(newWeight)
            }
        },
        placeholder = {
            Text(
                text = "0.0",
                color = blackColor
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
        ),
        maxLines = 1,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = blackColor,
            focusedBorderColor = cyanColor,
            unfocusedBorderColor = cyanColor,
            trailingIconColor = blackColor,
            placeholderColor = grayColor,
        ),
        shape = RoundedCornerShape(10.dp),
        trailingIcon = {
            Text(
                text = "kg",
                color = blackColor
            )
        },
        modifier = modifier
            .width(100.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PlasticWeightTextFieldPreview() {
    ClasticTheme {
        PlasticWeightTextField(
            onValueChange = {}
        )
    }
}
