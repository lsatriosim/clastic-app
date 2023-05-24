package com.example.clastic.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.clastic.R

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
}

@Composable
fun ProductKnowledgeComponent(modifier: Modifier = Modifier, onClick:(String)->Unit) {
    Column(modifier = modifier.padding(4.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(color = Color("#47ACD8".toColorInt())).clickable { onClick("PET") },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(55.dp),
                painter = painterResource(id = R.drawable.logo_bottle_pet),
                contentDescription = "logo recycle",
                tint = Color.White,
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = "PET", modifier = Modifier, color = Color.Black, fontSize = 16.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductKnowledgeComponentPreview() {
    ProductKnowledgeComponent(onClick = {})
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen(modifier = Modifier)
}