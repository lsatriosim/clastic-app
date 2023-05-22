package com.example.clastic


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListArticleScreen(modifier: Modifier = Modifier
    .fillMaxWidth()
    .height(175.dp)) {
    Box(modifier = modifier){
        Card(
            modifier = modifier
                .clip(RoundedCornerShape(10.dp)),
            elevation = 10.dp
        ) {
            Image(
                painter = painterResource(id = R.drawable.article1),
                contentDescription = "article",
                modifier = modifier.blur(1.dp),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
            )
            Box(modifier = Modifier, contentAlignment = Alignment.BottomStart) {
                ArticleData(Modifier)
            }
        }
    }
}

@Composable
fun ArticleData(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.padding(16.dp)) {
        TitleWithShadow(
            text = "Scientist Convert Clastic Waste into Vanilla Flavoring",
            modifier = Modifier
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_clock_white),
                tint = Color.White,
                contentDescription = null,
                modifier = Modifier.shadow(elevation = 3.dp),
            )
            TextWithShadow(
                text = "13 May 2023",
                modifier = Modifier.padding(end = 10.dp),
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_person_white),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.shadow(elevation = 3.dp),
            )
            TextWithShadow(
                text = "Author Name",
                modifier = Modifier.padding(end = 10.dp),
            )
        }
    }
}

@Composable
fun TextWithShadow(
    text: String,
    modifier: Modifier
) {
    Box(modifier = Modifier){
        Text(
            text = text,
            color = Color.DarkGray,
            modifier = modifier
                .offset(
                    x = 2.dp,
                    y = 2.dp
                )
                .alpha(0.75f)
        )
        Text(
            text = text,
            color = Color.White,
            modifier = modifier
        )
    }
}

@Composable
fun TitleWithShadow(
    text: String,
    modifier: Modifier
) {
    Box(modifier = Modifier){
        Text(
            text = text,
            color = Color.DarkGray,
            fontSize = 24.sp,
            maxLines = 2,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .offset(
                    x = 2.dp,
                    y = 2.dp
                )
                .alpha(0.75f)
        )
        Text(
            text = text,
            color = Color.White,
            fontSize = 24.sp,
            maxLines = 2,
            modifier = Modifier,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
        )
    }
}


@Preview(showBackground = false)
@Composable
fun ListArticlePreview() {
    ListArticleScreen()
}

@Preview(showBackground = false)
@Composable
fun TitlePreview() {
    ArticleData()
}