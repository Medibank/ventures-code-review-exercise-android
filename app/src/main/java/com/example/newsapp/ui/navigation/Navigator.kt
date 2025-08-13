package com.example.newsapp.ui.navigation

interface Navigator {

    fun navigateToSplash()

    fun navigateToNewsList()

    fun navigateToWebPage(newsUrlParcelable: String)
}