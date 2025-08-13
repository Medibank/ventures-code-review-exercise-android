package com.example.newsapp.ui.features.news

import com.example.newsapp.common.Constants
import com.example.newsapp.common.Resource
import com.example.newsapp.data.model.Source
import com.example.newsapp.domain.model.news.Article
import com.example.newsapp.domain.usecase.GetNewsListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsListViewModelTest {
    private val getNewsListUseCase = mockk<GetNewsListUseCase>()

    private val viewModel = NewsListViewModel(getNewsListUseCase)

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getNews should update news StateFlow with Resource Loading`() {
        val expected = Resource.Loading

        coEvery { getNewsListUseCase(Constants.API_NEWS_TOKEN, any()) } returns flowOf(expected)

        viewModel.getNews()

        assertEquals(expected, viewModel.news.value)
    }

    @Test
    fun `getNews should update news StateFlow with Resource Success`() {
        val expected = Resource.Success(listOf(
            Article(
                author = "John Doe",
                content = "This is the content of article 1",
                description = "Description of article 1",
                publishedAt = "2023-05-12T10:00:00Z",
                source = Source(name = "Example News", id = "1"),
                title = "Article 1",
                url = "https://www.example.com/article1",
                urlToImage = "https://www.example.com/image1.jpg"
            ),
            Article(
                author = "Jane Smith",
                content = "This is the content of article 2",
                description = "Description of article 2",
                publishedAt = "2023-05-13T12:30:00Z",
                source = Source(name = "Another News Outlet", id = "2"),
                title = "Article 2",
                url = "https://www.example.com/article2",
                urlToImage = "https://www.example.com/image2.jpg"
            ),
        ))

        coEvery { getNewsListUseCase(Constants.API_NEWS_TOKEN, any()) } returns flowOf(expected)

        viewModel.getNews()

        assertEquals(expected, viewModel.news.value)
    }

    @Test
    fun `getNews should update news StateFlow with Resource Error`() {
        val expected = Resource.Error("An error occurred")

        coEvery { getNewsListUseCase(Constants.API_NEWS_TOKEN, any()) } returns flowOf(expected)

        viewModel.getNews()

        assertEquals(expected, viewModel.news.value)
    }}