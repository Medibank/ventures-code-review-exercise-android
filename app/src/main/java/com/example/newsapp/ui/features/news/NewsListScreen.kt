package com.example.newsapp.ui.features.news

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsapp.R
import com.example.newsapp.common.Resource
import com.example.newsapp.domain.model.news.Article
import com.example.newsapp.ui.navigation.Navigator
import com.example.newsapp.ui.theme.NewsComposeTheme


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsListScreen(navigator: Navigator, viewModel: NewsListViewModel = hiltViewModel()) {
    val uiState by viewModel.news.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getNews()
    }

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    NewsComposeTheme  {
        Scaffold (
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.news
                            )
                        )
                    },
                    scrollBehavior = scrollBehavior,
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Blue,
                        scrolledContainerColor = Color.Transparent
                    ),
                )
            },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {


                ArticleList(
                    uiState = uiState,
                    navigator,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

            }
        }
    }
}

@Composable
fun ArticleList(
    uiState: Resource<List<Article>>,
    navigator: Navigator,
    modifier: Modifier
) {
    when (uiState) {
        is Resource.Loading -> {
            // Show a loading indicator
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(modifier = Modifier)
            }
        }

        is Resource.Success -> {
            val articles = uiState.data

            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                items(articles) { article ->
                    ArticleCard(article = article, onClick = { navigator.navigateToWebPage(article.url)})
                }
            }
        }

        is Resource.Error -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier.fillMaxHeight()
            ) {
                Image(
                    painter = painterResource(R.drawable.error_no_internet),
                    contentDescription = null,
                )
                Text(
                    text = uiState.errorMessage,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(top = 120.dp, start = 16.dp, end = 16.dp),
                    color = Color.Black
                )
            }
        }
    }

}



@Preview
@Composable
fun PreviewNewsListScreen() {
    //NewsListScreen(null)
}