package com.example.newsapp.domain.usecase

import com.example.newsapp.common.Constants
import com.example.newsapp.common.Resource
import com.example.newsapp.data.model.Source
import com.example.newsapp.data.model.news.ArticleDto
import com.example.newsapp.data.model.news.NewsResponse
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.domain.model.news.Article
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.util.logging.Logger

class GetNewsListUseCaseTest {

    private lateinit var newsRepository: NewsRepository
    private lateinit var getNewsListUseCase: GetNewsListUseCase
    private lateinit var logger: Logger
    private lateinit var articleDtoList: List<ArticleDto>
    private lateinit var articleList: List<Article>

    val newsResponseMock = mockk<Response<NewsResponse>>()

    @Before
    fun setup() {
        newsRepository = mockk()
        logger = mockk()
        getNewsListUseCase = GetNewsListUseCase(logger)

        articleDtoList = listOf(
            ArticleDto(
                author = "John Doe",
                content = "This is the content of article 1",
                description = "Description of article 1",
                publishedAt = "2023-05-12T10:00:00Z",
                source = Source(name = "Example News", id = "1"),
                title = "Article 1",
                url = "https://www.example.com/article1",
                urlToImage = "https://www.example.com/image1.jpg"
            ),
            ArticleDto(
                author = "Jane Smith",
                content = "This is the content of article 2",
                description = "Description of article 2",
                publishedAt = "2023-05-13T12:30:00Z",
                source = Source(name = "Another News Outlet", id = "2"),
                title = "Article 2",
                url = "https://www.example.com/article2",
                urlToImage = "https://www.example.com/image2.jpg"
            ),
        )

        articleList = listOf(
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
        )
    }

    @Test
    fun testGetNewsListUseCase() = runBlocking {
        val token = Constants.API_NEWS_TOKEN
        val country = "us"

        coEvery { newsResponseMock.isSuccessful } returns true
        coEvery { runBlocking { newsResponseMock.body()?.articles } } returns articleDtoList
        coEvery { newsRepository.getNews(token, country) } returns newsResponseMock

        val result: Flow<Resource<List<Article>>> = getNewsListUseCase.invoke(token, country)

        val resource = mutableListOf<Resource<List<Article>>>()
        launch {
            result.collect {
                resource.add(it)
            }
        }

        delay(1000)
        assertEquals(Resource.Loading, resource.first())
        assertEquals(Resource.Success(articleList), resource.last())
    }

}