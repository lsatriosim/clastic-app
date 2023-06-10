package com.example.clastic.ui.screen.transaction.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clastic.R
import com.example.clastic.ui.theme.ClasticTheme

@Composable
fun TransactionResultList(
    plasticType: String,
    plasticWeight: Float,
    totalPoints: Int,
    modifier: Modifier = Modifier
) {

    val cyanColor = colorResource(R.color.cyan_primary)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Circle,
            tint = cyanColor,
            contentDescription = null,
            modifier = Modifier
                .padding(end = 20.dp)
                .size(15.dp)
        )
        Column(
            modifier = Modifier
        ) {
            Text(
                text = plasticType,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = cyanColor
            )
            Text(
                text = "$plasticWeight kg",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = formatNumber(totalPoints),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = cyanColor
        )
    }
}

private fun formatNumber(number: Int): String {
    val formattedNumber = formatNumber(number)
    return "+ $formattedNumber pts"
}

@Preview(showBackground = true)
@Composable
fun TransactionResultListPreview() {
    ClasticTheme {
        TransactionResultList(
            plasticType = "PET",
            plasticWeight = 2.38f,
            totalPoints = 1000,
        )
    }
}