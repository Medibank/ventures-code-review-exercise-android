package com.example.newsapp.data.model.news

import com.example.newsapp.data.model.Source
import com.example.newsapp.domain.model.news.Article
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleDto(
    @Json(name = "author")
    val author: String?,
    @Json(name = "content")
    val content: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "publishedAt")
    val publishedAt: String?,
    @Json(name = "source")
    val source: Source?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "urlToImage")
    val urlToImage: String?
)

fun ArticleDto.toArticle() = Article(
    author = author.orEmpty(),
    description = description.orEmpty(),
    content = content.orEmpty(),
    publishedAt = publishedAt.orEmpty(),
    source = source,
    title = title.orEmpty(),
    url = url.orEmpty(),
    urlToImage = urlToImage.orEmpty(),
)