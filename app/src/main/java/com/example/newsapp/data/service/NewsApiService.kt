package com.example.newsapp.data.service

import com.example.newsapp.common.Constants
import com.example.newsapp.data.model.news.NewsResponse
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("apiKey") apiKey: String = Constants.API_NEWS_TOKEN,
        @Query("country") country: String
    ): Response<NewsResponse>

}

