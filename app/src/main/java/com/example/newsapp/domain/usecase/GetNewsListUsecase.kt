package com.example.newsapp.domain.usecase

import com.example.newsapp.common.Resource
import com.example.newsapp.data.model.news.toArticle
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.domain.model.news.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.logging.Logger
import javax.inject.Inject

class GetNewsListUseCase @Inject constructor(
    private val logger: Logger
) {

    operator fun invoke(token: String, country: String): Flow<Resource<List<Article>>> {

        return flow {
            emit(Resource.Loading)

            val newsRepository: NewsRepository = NewsRepository()
            val newsResponse = newsRepository.getNews(token,country)

            if (newsResponse.isSuccessful) {
                val articles = newsResponse.body()?.articles?.map { it.toArticle().apply {

                    content = content.takeIf { it?.isNotEmpty() == true } ?: "No Description Available"

                    author = author.takeIf { it?.isNotEmpty() == true } ?: "No Author Available"

                    title = title.takeIf { it?.isNotEmpty() == true } ?: "No Title Available"
                }
                }
                if (articles != null) {
                    emit(Resource.Success(articles)) // emit success state with filtered articles
                } else {
                    emit(Resource.Error("Error filtering articles"))
                }
            } else {
                emit(Resource.Error("Error fetching news")) // emit error state
            }
        }.catch { e ->
            emit(Resource.Error(e.message.toString()))
        }.flowOn(Dispatchers.IO)
    }
}