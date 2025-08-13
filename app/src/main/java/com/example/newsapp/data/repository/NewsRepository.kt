package com.example.newsapp.data.repository

import com.example.newsapp.data.model.news.NewsResponse
import com.example.newsapp.data.service.NewsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import okhttp3.OkHttpClient
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


class NewsRepository {

    private val apiService = Retrofit
        .Builder()
        .run {
            baseUrl("https://newsapi.org/v2/")
            client(OkHttpClient.Builder().build())
            addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build()))
            build()
        }.create(NewsApiService::class.java)

   suspend fun getNews(token: String, country: String): Response<NewsResponse> {
        return withContext(Dispatchers.IO) {
            apiService.getTopHeadlines(token, country)
        }
    }

}