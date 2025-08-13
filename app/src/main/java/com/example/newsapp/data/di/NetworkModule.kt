package com.example.newsapp.data.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.newsapp.data.utils.NetworkUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideConnectivityManager(@ApplicationContext appContext: Context): ConnectivityManager {
        return appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    fun provideNetworkUtils(connectivityManager: ConnectivityManager): NetworkUtils {
        return NetworkUtils(connectivityManager)
    }
}