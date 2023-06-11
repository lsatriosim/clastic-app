package com.example.clastic.ui.screen.mission

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import com.example.clastic.R
import com.example.clastic.data.entity.Mission
import com.example.clastic.data.entity.MissionData
import com.example.clastic.ui.screen.BottomBar
import com.example.clastic.ui.screen.listArticle.RecycleTag
import com.example.clastic.ui.screen.productKnowledge.PointTag

@Composable
fun MissionListScreen(modifier: Modifier = Modifier, onClick: (String) -> Unit, navHostController: NavHostController) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Mission", color = Color.White) },
            backgroundColor = Color("#1FA4BB".toColorInt())
        )
    },
    bottomBar = {
        BottomBar(currentMenu = "Mission", navController = navHostController)
    }){innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding).background(color = Color.White)){
            Box(
                modifier = modifier
                    .padding(8.dp)
            ) {
                LazyColumn{
                    items(MissionData.dummyMission, key = {it.title}){mission ->
                        MissionCard(mission = mission, onClick = onClick)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }

}

@Composable
fun MissionCard(modifier: Modifier = Modifier, mission: Mission, onClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .clickable { onClick(mission.title) },
        elevation = 10.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                ) {
                    Image(
                        painter = painterResource(id = mission.image),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .padding(18.dp), contentAlignment = Alignment.BottomStart
                    ) {
                        Text(
                            text = mission.title,
                            style = MaterialTheme.typography.h5.copy(color = Color.White),
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp), contentAlignment = Alignment.TopStart
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            RecycleTag(modifier = Modifier, tag = mission.tag)
                            PointTag(modifier = Modifier, point = mission.reward)
                            Text(
                                text = "20 Days Left",
                                style = MaterialTheme.typography.subtitle1.copy(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                ),
                                textAlign = TextAlign.End,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color("#0097B2".toColorInt())),
                ) {
                    Box(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 4.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = "Jalankan Misi",
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.h5.copy(
                                color = Color.White
                            )
                        )
                        Box(
                            modifier = modifier.fillMaxWidth(),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_forward_white),
                                contentDescription = null,
                            )
                        }
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MissionListPreview() {
    MissionListScreen(onClick = {}, navHostController = NavHostController(LocalContext.current))
}

@Preview(showBackground = true)
@Composable
fun MissionCardPreview() {
    Box(modifier = Modifier.padding(20.dp)) {
        MissionCard(modifier = Modifier, mission = MissionData.dummyMission[0], {})
    }
}