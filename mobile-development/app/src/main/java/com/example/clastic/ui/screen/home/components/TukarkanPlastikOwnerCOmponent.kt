package com.example.clastic.ui.screen.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.clastic.R

@Composable
fun TukarkanPlastikOwnerComponent(
    modifier: Modifier = Modifier,
    navigateToQrCode: () -> Unit,
    navigateToQRCodeScanner: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp), verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = "Tukarkan Plastikmu♻️",
                style = MaterialTheme.typography.h5.copy(color = Color.Black)
            )
        }
        Text(
            text = "Ayo tukarkan sisa plastikmu menjadi coin!!!",
            style = MaterialTheme.typography.subtitle1.copy(color = Color.Gray)
        )

        //content section: Tukarkan Plastikmu
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(3.dp),
            modifier = Modifier
                .height(100.dp)
        ) {
            //My Barcode
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .border(
                        2.dp, color = Color("#0198B3".toColorInt()),
                        RoundedCornerShape(8.dp)
                    )
                    .fillMaxHeight()
                    .clickable { navigateToQrCode() }
                    .padding(8.dp)
                    .background(color = Color.White)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = "My\nQR Code",
                        style = MaterialTheme.typography.subtitle1.copy(
                            color = Color("#0198B3".toColorInt()),
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Image(
                        painter = painterResource(id = R.drawable.qrcode),
                        contentDescription = null
                    )
                }
            }
        }
    }
}