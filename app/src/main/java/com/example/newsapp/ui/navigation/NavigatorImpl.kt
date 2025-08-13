package com.example.newsapp.ui.navigation

import androidx.navigation.NavHostController
import com.example.newsapp.common.Constants.WEB_URL_KEY

class NavigatorImpl(private val navController: NavHostController) : Navigator {


    override fun navigateToSplash() {
        navController.navigate(NavScreen.SplashScreen.route)
    }

    override fun navigateToNewsList() {
        navController.navigate(NavScreen.NewsListScreen.route)
    }

    override fun navigateToWebPage(newsUrl: String) {
        navController.currentBackStackEntry?.savedStateHandle?.set(WEB_URL_KEY,newsUrl)
        navController.navigate(NavScreen.WebPageScreen.route)
    }
}