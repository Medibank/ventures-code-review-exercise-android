package com.example.articlesheadlines.repository

import com.example.articlesheadlines.data.ArticleDao
import com.example.articlesheadlines.model.Article
import com.example.articlesheadlines.network.NewsApiService
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import org.mockito.Mockito.*

class NewsRepositoryTest {

    private val mockApi = mock(NewsApiService::class.java)
    private val mockDao = mock(ArticleDao::class.java)
    private val repository = NewsRepository(mockApi, mockDao)

    @Test
    fun testGetHeadlines() {
        val articles = listOf(Article("url", "title", "desc", "author", null))
        `when`(mockApi.getHeadlines("source")).thenReturn(Single.just(NewsApiService.HeadlinesResponse(articles)))
        repository.getHeadlines("source").test().assertValue(articles)
    }

    @Test
    fun testGetSavedArticles() {
        val articles = listOf(Article("url", "title", "desc", "author", null))
        `when`(mockDao.getAll()).thenReturn(flowOf(articles))
        val result = repository.getSavedArticles()
        // add assertions as needed
    }
}