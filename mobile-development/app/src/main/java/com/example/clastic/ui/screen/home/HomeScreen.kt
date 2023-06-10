package com.example.clastic.ui.screen.home

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.clastic.R
import com.example.clastic.data.entity.ProductData
import com.example.clastic.ui.screen.BottomBar
import com.example.clastic.ui.screen.ViewModelFactory
import com.example.clastic.ui.screen.home.components.MisiPlastikComponent
import com.example.clastic.ui.screen.home.components.ProductKnowledgeComponent
import com.example.clastic.ui.screen.home.components.TukarkanPlastikComponent
import java.text.NumberFormat

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    navController: NavController,
    navigateToQrCode: ()->Unit,
    navigateToDropPointMap: () -> Unit,
    navigateToQRCodeScanner: () -> Unit,
    onMissionClick: (String)->Unit,
    tutorialScreen: ()->Unit
) {
    val context = LocalContext.current
    val viewModel: HomeScreenViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context)
    )
    val listState = rememberLazyListState()
    val user by viewModel.user.collectAsState()
    val role by viewModel.role.collectAsState()

    Scaffold(
        bottomBar = { BottomBar(navController = navController, currentMenu = "Home")}
    ) { innerPadding ->
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
                            text = ("Hai, " + user?.username),
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
                                    text = if(user?.coin == null) "0 pts" else NumberFormat.getInstance().format(user?.coin).toString() + " pts",
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
                                IconButton(onClick = { tutorialScreen() }) {
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
            }
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                Box(
                    modifier = Modifier
                        .height(560.dp)
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
                        TukarkanPlastikComponent(
                            role = role,
                            modifier = modifier,
                            navigateToQrCode = navigateToQrCode,
                            navigateToDropPointMap = navigateToDropPointMap,
                            navigateToQRCodeScanner = navigateToQRCodeScanner,
                        )

                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                                .fillMaxWidth()
                                .background(color = Color("#F5F5F5".toColorInt()))
                                .alpha(0.6f)
                        )

                        MisiPlastikComponent(modifier = modifier, onMissionClick = onMissionClick)

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
                                text = "Yuk kenalan dengan jenis-jenis plastik!",
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

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen(
        onClick = {},
        navController = NavController(LocalContext.current),
        navigateToQrCode = {},
        tutorialScreen = {},
        onMissionClick = {},
        navigateToDropPointMap = {},
        navigateToQRCodeScanner = {}
    )
}