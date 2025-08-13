package com.example.newsapp.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.common.Constants
import com.example.newsapp.ui.features.news.NewsListScreen
import com.example.newsapp.ui.features.splash.SplashScreen
import com.example.newsapp.ui.features.webpage.WebPageScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val navigator = NavigatorImpl(navController)

    NavHost(
        navController = navController,
        startDestination = NavScreen.SplashScreen.route
    ) {
        composable(NavScreen.SplashScreen.route) {
            SplashScreen(navigator)
        }
        composable(NavScreen.NewsListScreen.route) {
            NewsListScreen(navigator)
        }

        composable(route = NavScreen.WebPageScreen.route) { backStackEntry ->
            val newsUrl = navController.previousBackStackEntry?.savedStateHandle?.get<String>(
                Constants.WEB_URL_KEY)


            if (newsUrl != null) {
                WebPageScreen(navigator = navigator, navController = navController, urlToRender = newsUrl)
            }
        }
    }
}