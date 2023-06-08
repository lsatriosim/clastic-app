package com.example.clastic.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt

@Composable
fun PopUpCard(modifier: Modifier = Modifier, closePopUp: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp, horizontal = 2.dp)
            .clip(
                RoundedCornerShape(12.dp)
            )
            .background(color = Color.White)
            .verticalScroll(rememberScrollState()),
        elevation = 8.dp
    ) {
        Box(
            modifier = modifier
        ) {
            Box(
                modifier = modifier.padding(vertical = 16.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Panduan\n Pengambilan Gambar",
                        style = MaterialTheme.typography.h5.copy(
                            color = Color("#02B4CC".toColorInt()),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    )
                    Column() {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CircleNumber(number = "1")
                            Text(
                                text = "Pastikan Pencahayaan yang Baik",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 48.dp, end = 16.dp),
                            text = "Pastikan foto memiliki pencahayaan yang cukup dan merata. Hindari kondisi pencahayaan yang terlalu terang atau terlalu gelap, karena hal ini dapat mempengaruhi kualitas gambar dan kesulitan dalam ekstraksi fitur"
                        )
                    }
                    Column() {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CircleNumber(number = "2")
                            Text(
                                text = "Hindari Bayangan dan Pantulan",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 48.dp, end = 16.dp),
                            text = "Pastikan tidak ada bayangan atau pantulan yang mengganggu pada objek gambar. Bayangan atau pantulan dapat mempengaruhi fitur objek yang terekam dan memperburuk hasil klasifikasi"
                        )
                    }
                    Column() {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CircleNumber(number = "3")
                            Text(
                                text = "Jaga Kepastian Fokus",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 48.dp, end = 16.dp),
                            text = "Pastikan objek utama pada goto dalam fokus yang jelas. Jika objek kabur atau tidak fokus, fitur yang diperoleh dari gambar tersebut mungkin tidak akurat dan dapat mempengaruhi hasil klasifikasi"
                        )
                    }
                    Column() {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CircleNumber(number = "4")
                            Text(
                                text = "Gunakan Background Polos",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 48.dp, end = 16.dp),
                            text = "Pastikan latar belakang gambar yang tidak mengganggu atau membingungkan. Latar belakang yang sederhana dan bersih membantu model dalam fokus pada objek yang akan diklasifikasikan"
                        )
                    }
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Button(
                            onClick = { closePopUp() },
                            modifier = Modifier.clip(RoundedCornerShape(60.dp)),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color("#1FA4BB".toColorInt())
                            )
                        )
                        {
                            Text(text = "Siap Klasifikasi!", style = MaterialTheme.typography.subtitle1.copy(color = Color.White), modifier = Modifier.padding(horizontal = 16.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CircleNumber(number: String) {
    Box(
        modifier = Modifier
            .width(24.dp)
            .height(24.dp)
            .clip(CircleShape)
            .background(color = Color("#02B4CC".toColorInt())),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = number,
            style = MaterialTheme.typography.subtitle1.copy(
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Preview(showBackground = false)
@Composable
fun PopUpPreview() {
    PopUpCard(closePopUp = {})
}

@Preview(showBackground = false)
@Composable
fun CircleNumberPreview() {
    CircleNumber("1")
}