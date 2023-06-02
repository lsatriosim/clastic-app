package com.example.clastic.ui.screen.profile.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clastic.R
import com.example.clastic.ui.theme.ClasticTheme

@Composable
fun ProfileSummary(
    totalTransaction: String,
    totalPlastic: String,
    modifier: Modifier = Modifier
) {
    val cyanPrimary = colorResource(R.color.cyan_primary)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .border(
                width = 2.dp,
                color = cyanPrimary,
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
            ) {
                Text(
                    text = totalTransaction,
                    fontSize = 24.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = stringResource(R.string.transaction_made),
                    color = Color.Black,
                    fontSize = 16.sp,
                )
            }
            Divider(
                color = cyanPrimary,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
            ) {
                Text(
                    text = totalPlastic + stringResource(R.string.kg),
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = stringResource(R.string.gathered_plastic),
                    color = Color.Black,
                    fontSize = 16.sp,
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ProfileSummaryPreview() {
    ClasticTheme {
        ProfileSummary(
            totalTransaction = "14",
            totalPlastic = "6.5"
        )
    }
}