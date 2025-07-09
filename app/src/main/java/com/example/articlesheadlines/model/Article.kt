package com.example.articlesheadlines.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "saved_articles")
data class Article(
    @PrimaryKey val url: String,
    val title: String,
    val description: String?,
    val author: String?,
    val urlToImage: String?
)