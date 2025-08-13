package com.example.newsapp.data.di

import com.example.newsapp.data.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideNewsRepository(newsRepositoryImpl: NewsRepository): NewsRepository {
        return NewsRepository()
    }

}