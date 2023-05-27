package com.example.clastic.ui.screen.productKnowledge

import android.util.EventLogTags.Description
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
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
import com.example.clastic.data.entity.Product
import com.example.clastic.data.entity.ProductData
import com.example.clastic.ui.screen.listArticle.RecycleTag

@Composable
fun ProductKnowledgeScreen(modifier: Modifier = Modifier, plasticType: PlasticKnowledge) {
    val listState = rememberLazyListState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = modifier) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                BannerWithGradient(imageName = plasticType.imageCover)
                Box(
                    modifier = modifier
                        .padding(8.dp)
                        .align(BottomStart),
                ) {
                    Text(
                        text = plasticType.fullName,
                        modifier = modifier,
                        style = MaterialTheme.typography.h4.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        ),
                        textAlign = TextAlign.Start
                    )
                }
                Box(
                    modifier = modifier
                        .align(Alignment.TopEnd)
                ) {
                    RecycleTag(modifier = Modifier, tag = plasticType.tag)
                }
            }
            Column(modifier = modifier.verticalScroll(rememberScrollState())) {
                Box(modifier = modifier.padding(18.dp)) {
                    Description(modifier = Modifier, description = plasticType.content)
                }
                Line()
                Box(modifier = modifier.padding(18.dp)) {
                    Column(modifier = Modifier) {
                        Text(
                            text = "Contoh barang",
                            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(bottom =6.dp)
                        )
                        LazyRow(state = listState) {
                            items(plasticType.productList, key = { it.productImage }) { product ->
                                ProductCard(product = product)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Line(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(5.dp)
            .background(color = Color("#F5F5F5".toColorInt())),
    )
}

@Composable
fun ProductCard(modifier: Modifier = Modifier, product: Product) {
    Card(
        modifier = modifier
            .padding(end = 12.dp)
            .width(140.dp)
            .shadow(elevation = 8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier) {
            Image(
                painter = painterResource(id = product.productImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = product.productName,
                    style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.ExtraBold)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCardScreen() {
    val data = ProductData
    ProductCard(product = data.plasticTypes[0].productList[0])
}

@Composable
fun BannerWithGradient(
    modifier: Modifier = Modifier, imageName: Int
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageName),
            contentDescription = null,
            modifier = modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp))
                .shadow(elevation = 2.dp),
            contentScale = ContentScale.Crop,
        )
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp))
                .alpha(0.5F)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color("#02B4CC".toColorInt()),
                            Color.Transparent
                        ),
                        startY = 0F,
                        endY = 400F
                    )
                ),
        )
    }
}

@Composable
fun Description(modifier: Modifier = Modifier, description: String) {
    Column(modifier = Modifier, horizontalAlignment = Alignment.Start) {
        Text(
            text = "Deskripsi",
            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = description,
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.subtitle1.copy(lineHeight = 25.sp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DescriptionPreview() {
    Description(description = ProductData.plasticTypes[0].content)
}

@Composable
fun PointTag(modifier: Modifier, point: String) {
    Box(modifier = modifier.padding(15.dp)) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(color = Color.White)
                .padding(horizontal = 12.dp, vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(modifier = Modifier, horizontalArrangement = Arrangement.Center) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_coin),
                    contentDescription = "recycle icon",
                    modifier = Modifier,
                    tint = Color("#02B4CC".toColorInt())
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = point,
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold,
                    color = Color("#0097B2".toColorInt())
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductKnowledgeScreenPreview(modifier: Modifier = Modifier) {
    ProductKnowledgeScreen(plasticType = ProductData.plasticTypes[0])
}

@Preview(showBackground = true)
@Composable
fun PointTagScreen(modifier: Modifier = Modifier) {
    PointTag(modifier = Modifier, point = "500pts")
}

