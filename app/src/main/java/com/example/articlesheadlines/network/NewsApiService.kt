package com.example.articlesheadlines.network

import com.example.articlesheadlines.model.Article
import com.example.articlesheadlines.model.Source
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/top-headlines")
    fun getHeadlines(
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): Single<HeadlinesResponse>

    @GET("v2/sources")
    fun getSources(
        @Query("language") language: String = "en",
        @Query("apiKey") apiKey: String = API_KEY
    ): Single<SourcesResponse>

    companion object {
        const val BASE_URL = "https://newsapi.org/"
        const val API_KEY = "YOUR_API_KEY_HERE" // <-- SET YOUR KEY HERE
    }
}

data class HeadlinesResponse(val articles: List<Article>)
data class SourcesResponse(val sources: List<Source>)