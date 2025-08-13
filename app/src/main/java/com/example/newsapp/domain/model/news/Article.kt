package com.example.newsapp.domain.model.news

import com.example.newsapp.data.model.Source

data class Article(
    var author: String?,
    var content: String?,
    var description: String?,
    var publishedAt: String?,
    var source: Source?,
    var title: String,
    var url: String,
    var urlToImage: String?
)