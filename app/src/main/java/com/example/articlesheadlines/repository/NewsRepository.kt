package com.example.articlesheadlines.repository

import android.content.Context
import com.example.articlesheadlines.data.ArticleDao
import com.example.articlesheadlines.data.UserPreferences
import com.example.articlesheadlines.model.Article
import com.example.articlesheadlines.model.Source
import com.example.articlesheadlines.network.NewsApiService
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

class NewsRepository(
    private val api: NewsApiService,
    private val articleDao: ArticleDao,
    private val context: Context
) {
    fun getHeadlines(sources: String): Single<List<Article>> =
        api.getHeadlines(sources).map { it.articles }

    fun getSources(): Single<List<Source>> =
        api.getSources().map { it.sources }

    fun getSavedArticles(): Flow<List<Article>> = articleDao.getAll()

    suspend fun saveArticle(article: Article) = articleDao.insert(article)

    suspend fun deleteArticle(article: Article) = articleDao.delete(article)

    suspend fun isArticleSaved(url: String): Boolean = articleDao.isSaved(url)

    fun getSelectedSources(): Flow<Set<String>> = UserPreferences.getSources(context)

    suspend fun setSelectedSources(sources: Set<String>) = UserPreferences.setSources(context, sources)
}