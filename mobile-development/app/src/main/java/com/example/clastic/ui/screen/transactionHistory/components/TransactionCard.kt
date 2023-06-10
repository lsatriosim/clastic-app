package com.example.clastic.ui.screen.transactionHistory.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.clastic.R
import com.example.clastic.ui.theme.ClasticTheme
import java.text.NumberFormat
import java.util.Locale

@Composable
fun TransactionCard(
    onClick: (String) -> Unit,
    id: String,
    date: String,
    location: String,
    points: Int,
    modifier: Modifier = Modifier
) {

    val cyanPrimary = colorResource(R.color.cyan_primary)

    Card(
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(3.dp, colorResource(R.color.cyan_textfield)),
        backgroundColor = Color.White,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(id) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier
                    .padding(10.dp)
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = date,
                    color = Color.Black
                )
                Text(
                    text = "Lokasi: $location",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = formatNumber(points),
                    fontWeight = FontWeight.Bold,
                    color = cyanPrimary
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.detail),
                    color = cyanPrimary,
                    fontWeight = FontWeight.Medium
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = cyanPrimary,
                    modifier = Modifier
                        .padding(end = 10.dp)
                )
            }
        }
    }
}

private fun formatNumber(number: Int): String {
    val numberFormat = NumberFormat.getInstance(Locale.getDefault())
    val formattedNumber = numberFormat.format(number)
    return "+ $formattedNumber pts"
}

@Preview(showBackground = false)
@Composable
fun TransactionCardPreview() {
    ClasticTheme {
        TransactionCard(
            id = "acoabcoiabocbao",
            date = "Senin, 12 Agustus 2023 jam 18:00:20",
            location = "RPTRA Dahlia",
            points = 13000,
            onClick = {}
        )
    }
}