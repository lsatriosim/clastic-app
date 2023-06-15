package com.example.clastic.ui.screen.listArticle


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.clastic.R
import com.example.clastic.ui.screen.ViewModelFactory
import com.example.clastic.data.entity.Article
import com.example.clastic.ui.screen.BottomBar

@Composable
fun ListArticleScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    onClick: (String) -> Unit,
    navController: NavController
) {
    val viewModel: ListArticleViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            LocalContext.current
        )
    )
    val articleListState by viewModel.articleList.collectAsState()
    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Article",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .padding(start = 15.dp),
                    )
                },
                backgroundColor = colorResource(R.color.cyan_primary),
            )
        },
        bottomBar = { BottomBar(currentMenu = "Article", navController = navController)}
    ) {innerPadding->
        Box(
            modifier = modifier.fillMaxSize().padding(innerPadding).background(color = Color.White)
        ){
            LazyColumn(state = listState){
                items(articleListState, key = {it.title}){article->
                    ListArticle(article = article, onClick = onClick)
                }
            }
        }
    }
}

@Composable
fun ListArticle(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(175.dp)
        .clip(RoundedCornerShape(10.dp))
        .padding(8.dp),
    article: Article,
    onClick: (String) -> Unit
) {
    Box(modifier = modifier.clickable { onClick(article.contentUrl) }) {
        Card(
            modifier = modifier,
            elevation = 10.dp
        ) {
            AsyncImage(
                model = article.posterUrl,
                contentDescription = "article",
                modifier = modifier.blur(1.dp),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
            )
            RecycleTag(modifier = Modifier, article.tag)
            Box(modifier = Modifier, contentAlignment = Alignment.BottomStart) {
                ArticleData(Modifier, article)
            }
        }
    }
}

@Composable
fun RecycleTag(modifier: Modifier, tag: String) {
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(color = Color.White)
                .padding(horizontal = 12.dp, vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(modifier = Modifier, horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_recycle_blue),
                    contentDescription = "recycle icon",
                    modifier = Modifier,
                    tint = Color("#0097B2".toColorInt())
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = tag,
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold,
                    color = Color("#0097B2".toColorInt())
                )
            }
        }
    }
}

@Composable
fun ArticleData(modifier: Modifier = Modifier, article: Article) {
    Column(modifier = Modifier.padding(16.dp)) {
        TitleWithShadow(
            text = article.title,
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
                text = article.createdAt,
                modifier = Modifier.padding(end = 10.dp),
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_person_white),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.shadow(elevation = 3.dp),
            )
            TextWithShadow(
                text = article.author,
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
    Box(modifier = Modifier) {
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
    Box(modifier = Modifier) {
        Text(
            text = text,
            color = Color.DarkGray,
            fontSize = 16.sp,
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
            fontSize = 16.sp,
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
    ListArticle(
        modifier = Modifier, article = Article(
            title = "Scientits Convert Clastic Waste into Vanilla Flavoring",
            posterUrl = "",
            author = "Author Name",
            tag = "PET",
            createdAt = "13 May 2023",
            contentUrl = ""
        ),
        onClick = {}
    )
}

@Preview(showBackground = false)
@Composable
fun TitlePreview() {
    ArticleData(
        modifier = Modifier, article = Article(
            title = "Scientits Convert Clastic Waste into Vanilla Flavoring",
            posterUrl = "",
            author = "Author Name",
            tag = "PET",
            createdAt = "13 May 2023",
            contentUrl = ""
        )
    )
}