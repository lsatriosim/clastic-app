package com.example.clastic.ui.screen.transaction.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clastic.ui.theme.ClasticTheme

@Composable
fun TransactionDetailItem(
    fieldName: String,
    fieldValue: String,
    modifier: Modifier = Modifier
) {
    val gray = Color.Gray
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        Text(
            text = fieldName,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = gray,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = fieldValue,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionDetailItemPreview() {
    ClasticTheme {
        TransactionDetailItem(
            fieldName = "Penerima",
            fieldValue = "SURYA"
        )
    }
}