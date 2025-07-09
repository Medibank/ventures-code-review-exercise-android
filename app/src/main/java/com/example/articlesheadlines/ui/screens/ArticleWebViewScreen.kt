package com.example.articlesheadlines.ui.screens

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.articlesheadlines.viewmodel.ArticleWebViewModel
import com.example.articlesheadlines.model.Article
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun ArticleWebViewScreen(
    url: String,
    viewModel: ArticleWebViewModel = viewModel()
) {
    val isSaved by viewModel.isSaved.collectAsState()
    // In a real app, you should pass the full Article, here we just have URL.
    // For demonstration, we'll create a dummy Article. You should fetch the real one.
    val dummyArticle = remember(url) {
        Article(url, url, null, null, null)
    }
    LaunchedEffect(url) { viewModel.checkSaved(url) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Article") },
                actions = {
                    IconButton(onClick = { viewModel.toggleSave(dummyArticle) }) {
                        Icon(
                            imageVector = if (isSaved) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = if (isSaved) "Saved" else "Save"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true
                    loadUrl(url)
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}