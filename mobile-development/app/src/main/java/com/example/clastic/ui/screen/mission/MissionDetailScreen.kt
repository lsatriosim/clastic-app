package com.example.clastic.ui.screen.mission

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.clastic.data.entity.Impact
import com.example.clastic.data.entity.Mission
import com.example.clastic.data.entity.MissionData
import com.example.clastic.ui.screen.listArticle.RecycleTag
import com.example.clastic.ui.screen.productKnowledge.BannerWithGradient
import com.example.clastic.ui.screen.productKnowledge.PointTag

@Composable
fun MissionDetailScreen(mission: Mission, joinCampaign: (String) -> Unit) {
    Scaffold(
        bottomBar = { MissionBottomBar(mission = mission, joinCampaign = joinCampaign) },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Mission Detail",
                        color = Color.White
                    )
                },
                backgroundColor = Color("#1FA4BB".toColorInt())
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = Color.White)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.verticalScroll(
                    rememberScrollState()
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp))
                ) {
                    BannerWithGradient(imageName = mission.image)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .padding(8.dp), contentAlignment = Alignment.TopEnd
                    ) {
                        RecycleTag(modifier = Modifier, tag = mission.tag)
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .padding(12.dp), contentAlignment = Alignment.BottomStart
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(
                                text = mission.title,
                                style = MaterialTheme.typography.h5.copy(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            PointTag(modifier = Modifier, point = mission.reward)
                        }
                    }

                }
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Deskripsi",
                        style = MaterialTheme.typography.h5.copy(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = mission.description,
                        style = MaterialTheme.typography.subtitle1.copy(color = Color.Gray)
                    )
                }
                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                        .fillMaxWidth()
                        .background(color = Color("#F5F5F5".toColorInt()))
                        .alpha(0.6f)
                )
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Same Yearly Impact",
                        style = MaterialTheme.typography.h5.copy(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "Misi ini akan setara dengan melakukan aktivitas berikut",
                        style = MaterialTheme.typography.subtitle1.copy(color = Color.Gray)
                    )
                    Box(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                ImpactCard(impact = mission.listImpact[0])
                                ImpactCard(impact = mission.listImpact[1])
                            }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                ImpactCard(impact = mission.listImpact[2])
                                ImpactCard(impact = mission.listImpact[3])
                            }
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun ImpactCard(impact: Impact) {
    Box(
        modifier = Modifier
            .width(165.dp)
            .height(115.dp)
            .clip(RoundedCornerShape(10.dp)), contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = impact.image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(
                text = impact.value,
                style = MaterialTheme.typography.h5.copy(color = Color.White, fontWeight = Bold),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = impact.key,
                style = MaterialTheme.typography.subtitle1.copy(color = Color.White),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            )
        }
    }
}

@Composable
fun MissionBottomBar(mission: Mission, joinCampaign: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 8.dp)
            .background(color = Color.White), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(vertical = 6.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Syarat dan Ketentuan Berlaku",
                style = MaterialTheme.typography.subtitle1.copy(color = Color("#02B4CC".toColorInt())),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Button(
                onClick = { joinCampaign(mission.title) },
                modifier = Modifier.clip(RoundedCornerShape(60.dp)),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color("#1FA4BB".toColorInt())
                )
            ) {
                Text(
                    text = "Submit",
                    style = MaterialTheme.typography.subtitle1.copy(color = Color.White),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MissionDetailPreview() {
    MissionDetailScreen(MissionData.dummyMission[0], {})
}

@Preview(showBackground = false)
@Composable
fun ImpactCardPreview() {
    ImpactCard(impact = MissionData.dummyMission[0].listImpact[0])
}
