package com.example.clastic.ui.screen.tutorial

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.clastic.R

@Composable
fun TutorialScreen(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.verticalScroll(
                rememberScrollState()
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(
                        RoundedCornerShape(
                            bottomEnd = 20.dp, bottomStart =
                            20.dp
                        )
                    )
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color("#0097B2".toColorInt()),
                                Color("#3FB9CF".toColorInt())
                            ),
                            startY = 0F,
                            endY = 600F
                        )
                    )
            ) {
                Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(color = Color.White)
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                            text = "How to Get More Points",
                            style = MaterialTheme.typography.subtitle1.copy(color = Color("#0097B2".toColorInt()))
                        )
                    }
                }
                Box(
                    modifier = modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .padding(12.dp), contentAlignment = Alignment.BottomStart
                ) {
                    Text(
                        text = "Berkontribusi Sehatkan Bumi dengan Plastik!",
                        style = MaterialTheme.typography.h5.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Tukarkan Plastikmu", style = MaterialTheme.typography.h5)
                        Icon(
                            painter = painterResource(id = R.drawable.ic_recycle_blue),
                            tint = Color.Green,
                            contentDescription = null
                        )
                    }
                    Text(
                        text = "Tukarkan sampah plastikmu dan dapatkan manfaat sebagai berikut :",
                        style = MaterialTheme.typography.subtitle1.copy(color = Color.Gray)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BenefitCard(benefit = "Tingkatkan kualitas hidup", benefitNumber = "1")
                        BenefitCard(benefit = "Dapatkan penghasilan tambahan", benefitNumber = "2")
                        BenefitCard(
                            benefit = "Meningkatkan kesehatan lingkungan",
                            benefitNumber = "3"
                        )
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .height(8.dp)
                    .fillMaxWidth()
                    .background(color = Color("#F5F5F5".toColorInt()))
                    .alpha(0.6f)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(horizontal = 12.dp)
            ) {
                Text(text = "Cara Menukarkan Plastikmu", style = MaterialTheme.typography.h5)
                Text(
                    text = "Tukarkan sampah plastikmu dan dapatkan manfaat sebagai berikut :",
                    style = MaterialTheme.typography.subtitle1.copy(color = Color.Gray)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        CircleNumberTutor("1")
                        Text(text = "Datangi Drop Point yang ada pada menu:  \uD83D\uDCCC Drop Off dengan membawa sampah plastik yang sudah Kamu pilah")
                    }
                    Row(
                        modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        CircleNumberTutor("2")
                        Text(text = "Klik tab 🏠 Home pada laman utama dan lihat section Tukarkan Plastikmu ♻️")
                    }
                    Row(
                        modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        CircleNumberTutor("3")
                        Text(text = "Tunjukkan QR Code Kamu kepada Petugas di Drop Point")
                    }
                    Row(
                        modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        CircleNumberTutor("4")
                        Text(text = "Tunggu hingga petugas menghitung plasik yang kamu bawa dan pastikan petugas mengisi informasi dengan benar")
                    }
                    Row(
                        modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        CircleNumberTutor("5")
                        Text(text = "Tunggu beberapa saat dan Points akan masuk ke akun kamu.")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun BenefitCard(modifier: Modifier = Modifier, benefit: String, benefitNumber: String) {

    Column(
        verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .width(105.dp)
            .height(140.dp)
            .border(width = 1.dp, colorResource(id = R.color.cyan_textfield), shape = RoundedCornerShape(20.dp))
            .padding(8.dp)
    ) {
        Text(text = benefit, style = MaterialTheme.typography.subtitle1)
        Text(
            text = benefitNumber,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.h5.copy(
                color = Color("#0097B2".toColorInt()),
                fontWeight = FontWeight.Bold
            )
        )
    }

}

@Composable
fun CircleNumberTutor(number: String) {
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

@Preview(showBackground = true)
@Composable
fun TutorialPreview() {
    TutorialScreen()
}

@Preview(showBackground = false)
@Composable
fun BenefitCardPreview() {
    BenefitCard(benefit = "Tingkatkan kualitas hidup", benefitNumber = "1")
}