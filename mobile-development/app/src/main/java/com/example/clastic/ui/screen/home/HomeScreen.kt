package com.example.clastic.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.clastic.R
import com.example.clastic.data.entity.PlasticKnowledge
import com.example.clastic.data.entity.ProductData
import com.example.clastic.ui.screen.listArticle.RecycleTag
import com.example.clastic.ui.screen.productKnowledge.PointTag

@Composable
fun HomeScreen(modifier: Modifier = Modifier, onClick: (String) -> Unit) {
    val listState = rememberLazyListState()
    LazyRow(state = listState, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        items(items = ProductData.plasticTypes, key = { it.tag }) { plasticType ->
            ProductKnowledgeComponent(onClick = onClick, plasticType = plasticType)
        }
    }
}

@Composable
fun ProductKnowledgeComponent(
    modifier: Modifier = Modifier,
    plasticType: PlasticKnowledge,
    onClick: (String) -> Unit
) {
    Column(
        modifier = modifier.padding(4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(color = Color("#47ACD8".toColorInt()))
                .clickable { onClick(plasticType.tag) },
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
        Text(
            text = plasticType.tag,
            modifier = Modifier,
            color = Color.Black,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "PET",
            modifier = Modifier,
            color = Color.Black,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = plasticType.tag,
            modifier = Modifier,
            color = Color.Black,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductKnowledgeComponentPreview() {
    ProductKnowledgeComponent(
        onClick = {},
        plasticType = PlasticKnowledge("", "", 0, "", emptyList())
    )
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen(onClick = {})
}

@Composable
fun MissionCard(modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .clickable { }
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
                        painter = painterResource(id = R.drawable.article1),
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
                            text = "Kumpulkan Plastik Rumah Tangga",
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
                            RecycleTag(modifier = Modifier, tag = "PET")
                            PointTag(modifier = Modifier, point = "500pts")
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
fun MissionCardPreview() {
    Box(modifier = Modifier.padding(20.dp)) {
        MissionCard(modifier = Modifier)
    }
}