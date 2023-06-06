package com.example.clastic.ui.screen.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.example.clastic.R
import com.example.clastic.data.entity.PlasticKnowledge
import com.example.clastic.data.entity.ProductData
import com.example.clastic.ui.screen.BottomBar
import com.example.clastic.ui.screen.listArticle.RecycleTag
import com.example.clastic.ui.screen.productKnowledge.PointTag

@Composable
fun HomeScreen(modifier: Modifier = Modifier, onClick: (String) -> Unit, navController: NavController) {
    val listState = rememberLazyListState()
    Scaffold(
        bottomBar = { BottomBar(navController = navController, currentMenu = "Home")}
    ) {innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color("#3FB9CF".toColorInt()),
                            Color("#0097B2".toColorInt())
                        ),
                        startX = 0F,
                        endX = 800F
                    )
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopStart
            ) {
                Box(
                    modifier = modifier.padding(horizontal = 2.dp, vertical = 4.dp),
                    contentAlignment = Alignment.TopStart
                ) {
                    Box(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(8.dp), contentAlignment = Alignment.TopEnd
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_notification_white),
                            tint = Color.White,
                            contentDescription = null
                        )
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.clastic_logo_text),
                            contentDescription = null,
                            modifier = Modifier
                                .width(196.dp)
                                .height(40.dp)
                        )
                        Text(
                            text = "Hai, Liefran!",
                            style = MaterialTheme.typography.h5.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            ),
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(color = Color.White)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                modifier = Modifier.padding(4.dp),
                            ) {
                                Icon(
                                    modifier = Modifier,
                                    painter = painterResource(id = R.drawable.ic_coin),
                                    tint = Color("#0198B3".toColorInt()),
                                    contentDescription = null
                                )
                                Text(
                                    modifier = Modifier,
                                    text = "2,000.00 pts",
                                    style = MaterialTheme.typography.subtitle1.copy(
                                        color = Color("#0198B3".toColorInt()),
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                        }
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.align(
                                    Alignment.Center
                                )
                            ) {
                                Text(
                                    text = "Want to get more points?",
                                    style = MaterialTheme.typography.subtitle1.copy(
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_question_white),
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        }
                    }
                }
            }
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                Box(
                    modifier = Modifier
                        .height(616.dp)
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        .background(color = Color.White)
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.verticalScroll(
                            rememberScrollState()
                        )
                    ) {
                        //section: Tukarkan Plastikmu
                        //title section: Tukarkan Plastikmu
                        tukarkanPlastikComponent(modifier = modifier)

                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                                .fillMaxWidth()
                                .background(color = Color("#F5F5F5".toColorInt()))
                                .alpha(0.6f)
                        )

                        MisiPlastikComponent(modifier = modifier)

                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                                .fillMaxWidth()
                                .background(color = Color("#F5F5F5".toColorInt()))
                                .alpha(0.6f)
                        )
                        //List Product Knowledge
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(2.dp),
                            ) {
                                Text(
                                    text = "Jenis-jenis Plastik",
                                    style = MaterialTheme.typography.h5.copy(color = Color.Black)
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_trash),
                                    contentDescription = null,
                                    tint = Color.Gray
                                )
                            }
                            Text(
                                text = "Ayo tukarkan sisa plastikmu menjadi coin!!!",
                                style = MaterialTheme.typography.subtitle1.copy(color = Color.Gray)
                            )

                            LazyRow(
                                state = listState,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                val listColor = listOf(
                                    Color("#8DCF92".toColorInt()),
                                    Color("#4D9E3F".toColorInt()),
                                    Color("#D17021".toColorInt()),
                                    Color("#47ACD8".toColorInt()),
                                    Color("#0387B8".toColorInt()),
                                    Color("#614E9D".toColorInt()),
                                    Color("#707176".toColorInt()),
                                )
                                items(
                                    items = ProductData.plasticTypes,
                                    key = { it.tag }) { plasticType ->
                                    val colorIndex = ProductData.plasticTypes.indexOf(plasticType) %listColor.size
                                    ProductKnowledgeComponent(
                                        onClick = onClick,
                                        plasticType = plasticType,
                                        backgroundColor = listColor[colorIndex]
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MisiPlastikComponent(modifier: Modifier = Modifier) {
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
                text = "Misi Plastik",
                style = MaterialTheme.typography.h5.copy(color = Color.Black)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_campaign_white),
                contentDescription = null,
                tint = Color.Gray
            )
        }
        Text(
            text = "Ayo tukarkan sisa plastikmu menjadi coin!!!",
            style = MaterialTheme.typography.subtitle1.copy(color = Color.Gray)
        )

        //content section: Tukarkan Plastikmu
        MissionCard()
    }
}

@Composable
fun tukarkanPlastikComponent(modifier: Modifier = Modifier) {
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
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            //Drop Off Point
            Box(
                modifier = Modifier
                    .border(
                        2.dp, color = Color("#0198B3".toColorInt()),
                        RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp)
                    .background(color = Color.White)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(2.dp)
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
            //My Barcode
            Box(
                modifier = Modifier
                    .border(
                        2.dp, color = Color("#0198B3".toColorInt()),
                        RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp)
                    .background(color = Color.White)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = "My\nBarcode",
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

@Composable
fun ProductKnowledgeComponent(
    modifier: Modifier = Modifier,
    plasticType: PlasticKnowledge,
    backgroundColor: Color,
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
                .background(color = backgroundColor)
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
    }
}

@Preview(showBackground = true)
@Composable
fun ProductKnowledgeComponentPreview() {
    ProductKnowledgeComponent(
        onClick = {},
        plasticType = PlasticKnowledge("", "", 0, "", emptyList()),
        backgroundColor = Color.Blue
    )
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen(onClick = {}, navController = NavController(LocalContext.current))
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