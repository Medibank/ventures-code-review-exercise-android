package com.example.articlesheadlines.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.articlesheadlines.viewmodel.SavedViewModel
import com.example.articlesheadlines.model.Article
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.foundation.Image

@Composable
fun SavedScreen(
    navController: NavController,
    viewModel: SavedViewModel = viewModel()
) {
    val savedArticles by viewModel.savedArticles.collectAsState()
    if (savedArticles.isEmpty()) {
        Text(
            text = "No saved articles.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        )
    } else {
        LazyColumn {
            items(savedArticles) { article ->
                SavedArticleRow(
                    article = article,
                    onClick = { navController.navigate("article/${article.url}") },
                    onDelete = { viewModel.deleteArticle(article) }
                )
                Divider()
            }
        }
    }
}

@Composable
fun SavedArticleRow(article: Article, onClick: () -> Unit, onDelete: (() -> Unit)? = null) {
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
        onDelete?.let {
            IconButton(onClick = it) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}