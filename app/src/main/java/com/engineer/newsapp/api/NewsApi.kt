package com.engineer.newsapp.api

import com.engineer.newsapp.models.NewsResponse
import com.engineer.newsapp.models.SourceResponse
import com.engineer.newsapp.util.ApiConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country")
        country: String = "us",
        @Query("apiKey")
        apiKey: String = ApiConstants.NEWS_API_KEY
    ):Response<NewsResponse>

    @GET("v2/top-headlines")
    suspend fun getTopHeadlinesFromSources(
        @Query("sources")
        sources: String,
        @Query("apiKey")
        apiKey: String = ApiConstants.NEWS_API_KEY
    ):Response<NewsResponse>

    @GET("/v2/sources")
    suspend fun getNewsSources(
        @Query("language") country: String = "en",
        @Query("apiKey") apiKey: String = ApiConstants.NEWS_API_KEY,
    ): Response<SourceResponse>
}