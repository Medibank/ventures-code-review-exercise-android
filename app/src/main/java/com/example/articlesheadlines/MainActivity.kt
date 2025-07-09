package com.example.articlesheadlines

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.articlesheadlines.ui.AppNavHost
import com.example.articlesheadlines.ui.theme.ArticlesHeadlinesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArticlesHeadlinesTheme {
                AppNavHost()
            }
        }
    }
}