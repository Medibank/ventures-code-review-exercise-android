package com.example.newsapp.ui.features.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.domain.model.news.Article
import coil3.compose.AsyncImage

@Composable
fun ArticleCard(article: Article, onClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 2.dp)
            .background(Color.LightGray)
    ) {
        if(article.urlToImage != null) {
            AsyncImage(
                model = article.urlToImage,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.width(100.dp).wrapContentHeight().padding(top = 8.dp, start = 8.dp)
            )
        } else {
            Image(
                painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(64.dp)

            )
        }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f).padding(8.dp)) {
            Text(
                article.title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.DarkGray
            )
            article.description?.let {
                Text(
                    it,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 2,
                    color = Color.Black
                )
            }
            article.author?.let {
                Text(
                    "by $it",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = Color.DarkGray,
                )
            }
        }
    }
}

