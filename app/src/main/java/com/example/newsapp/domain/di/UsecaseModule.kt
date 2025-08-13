package com.example.newsapp.domain.di

import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.domain.usecase.GetNewsListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import java.util.logging.Logger
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun providesLogger(): Logger {
        return Logger.getLogger("")
    }

    @Provides
    fun provideGetNewsListUseCase(logger: Logger): GetNewsListUseCase {
        return GetNewsListUseCase(logger)
    }
}