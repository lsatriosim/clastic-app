package com.example.clastic.ui.screen.transaction.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clastic.R
import com.example.clastic.ui.theme.ClasticTheme
import java.text.NumberFormat
import java.util.Locale

@Composable
fun PlasticInput(
    id: Int,
    onValueChanged: (Float, Int, Int) -> Unit,
    onTypeSelected: (String, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var totalPoints by rememberSaveable {
        mutableStateOf(0)
    }
    val cyanColor = colorResource(R.color.cyan_textfield)

    val formattedPoints = rememberSaveable(totalPoints) {
        val numberFormat = NumberFormat.getInstance(Locale.getDefault())
        numberFormat.format(totalPoints)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        PlasticDropDownMenu(
            onValueChange = { newValue->
                onTypeSelected(newValue, id)
            },
            modifier = Modifier
                .padding(end = 10.dp)
        )
        PlasticWeightTextField(
            // TODO(change static point data)
            onValueChange = { newValue ->
                val weightTotal = newValue.toFloatOrNull() ?: 0f
                totalPoints = (weightTotal * 3000).toInt()
                onValueChanged(weightTotal, totalPoints, id)
            }
        )
        Text(
            text = "=",
            fontSize = 30.sp,
            color = cyanColor,
            modifier = Modifier
                .padding(horizontal = 10.dp)
        )
        Text(
            text = "$formattedPoints pts",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = cyanColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlasticInputPreview() {
    ClasticTheme {
        PlasticInput(
            id = 1,
            onValueChanged = { _, _, _ ->

            },
            onTypeSelected = { _, _, ->

            }
        )
    }
}