package com.example.newsapp.ui.navigation

sealed class NavScreen(val route: String) {

    object SplashScreen : NavScreen("splash")
    object NewsListScreen : NavScreen("news_list")
    object WebPageScreen : NavScreen("webPage")

}