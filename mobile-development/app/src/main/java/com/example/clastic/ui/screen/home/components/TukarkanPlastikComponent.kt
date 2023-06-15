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
fun TukarkanPlastikComponent(
    role: String,
    modifier: Modifier = Modifier,
    navigateToQrCode: () -> Unit,
    navigateToDropPointMap: () -> Unit,
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
            if (role == "owner") {
                //Scan QR Code
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .border(
                            2.dp, color = Color("#0198B3".toColorInt()),
                            RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp)
                        .background(color = Color.White)
                        .clickable { navigateToQRCodeScanner() }
                        .fillMaxHeight()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.spacedBy(2.dp),
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                Text(
                                    text = "Scan QR Code",
                                    style = MaterialTheme.typography.subtitle1.copy(
                                        color = Color("#0198B3".toColorInt()),
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Icon(
                                    imageVector = Icons.Default.QrCodeScanner,
                                    contentDescription = null,
                                    tint = colorResource(R.color.cyan_primary)
                                )
                            }
                            Text(
                                text = "Scan QR Code pengguna untuk\nmelakukan transaksi",
                                style = MaterialTheme.typography.caption.copy(color = Color.Black)
                            )
                            Spacer(modifier = modifier.height(1.dp))
                        }
                        Icon(
                            painter = painterResource(id = R.drawable.ic_forward_white),
                            contentDescription = null,
                            tint = Color("#0198B3".toColorInt())
                        )
                    }
                }
            } else {
                //Drop Off Point
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .border(
                            2.dp, color = Color("#0198B3".toColorInt()),
                            RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp)
                        .background(color = Color.White)
                        .clickable { navigateToDropPointMap() }
                        .fillMaxHeight()
                ) {
                    //Drop Off
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.spacedBy(2.dp),
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                Text(
                                    text = "Drop Off Point",
                                    style = MaterialTheme.typography.subtitle1.copy(
                                        color = Color("#0198B3".toColorInt()),
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_location_white),
                                    contentDescription = null,
                                    tint = Color.Red
                                )
                            }
                            Text(
                                text = "Pilih Drop Off Point terdekat\ndan tukarkan plastikmu!",
                                style = MaterialTheme.typography.caption.copy(color = Color.Black)
                            )
                            Spacer(modifier = modifier.height(1.dp))
                        }
                        Icon(
                            painter = painterResource(id = R.drawable.ic_forward_white),
                            contentDescription = null,
                            tint = Color("#0198B3".toColorInt())
                        )
                    }
                }
            }

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