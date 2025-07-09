package com.example.articlesheadlines.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.articlesheadlines.viewmodel.HeadlinesViewModel
import com.example.articlesheadlines.model.Article
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.ui.Alignment

@Composable
fun HeadlinesScreen(
    navController: NavController,
    viewModel: HeadlinesViewModel = viewModel()
) {
    val headlines by viewModel.headlines.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val selectedSources by viewModel.selectedSources.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            error != null -> ErrorBanner(error!!)
            headlines.isEmpty() -> NoSourcesBanner()
            else -> LazyColumn {
                items(headlines) { article ->
                    ArticleRow(article) {
                        navController.navigate("article/${article.url}")
                    }
                    Divider()
                }
            }
        }
    }
}

@Composable
fun ArticleRow(article: Article, onClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(article.urlToImage),
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(article.title, style = MaterialTheme.typography.subtitle1)
            article.description?.let {
                Text(it, style = MaterialTheme.typography.body2, maxLines = 2)
            }
            article.author?.let {
                Text("by $it", style = MaterialTheme.typography.caption)
            }
        }
    }
}

@Composable
private fun ErrorBanner(msg: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Icon(Icons.Default.Error, contentDescription = null, tint = MaterialTheme.colors.error)
        Spacer(Modifier.width(8.dp))
        Text(msg, color = MaterialTheme.colors.error)
    }
}

@Composable
private fun NoSourcesBanner() {
    Text(
        "No sources selected. Please select sources in the Sources tab.",
        style = MaterialTheme.typography.body1,
        // modifier = Modifier.align(Alignment.Center)
    )
}

