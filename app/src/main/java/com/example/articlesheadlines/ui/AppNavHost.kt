package com.example.articlesheadlines.ui

// import androidx.compose.material.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.articlesheadlines.ui.screens.*
import com.example.articlesheadlines.viewmodel.*

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    object Headlines : BottomNavItem("headlines", "Headlines", Icons.Default.Home)
    object Sources : BottomNavItem("sources", "Sources", Icons.Default.List)
    object Saved : BottomNavItem("saved", "Saved", Icons.Default.Bookmark)
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val items = listOf(BottomNavItem.Headlines, BottomNavItem.Sources, BottomNavItem.Saved)
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route?.substringBefore("/")
                items.forEach { item ->
                    BottomNavigationItem(
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = BottomNavItem.Headlines.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Headlines.route) { HeadlinesScreen(navController) }
            composable(BottomNavItem.Sources.route) { SourcesScreen() }
            composable(BottomNavItem.Saved.route) { SavedScreen(navController) }
            composable(
                "article/{url}",
                arguments = listOf(navArgument("url") { type = NavType.StringType })
            ) { backStackEntry ->
                val url = backStackEntry.arguments?.getString("url")
                if (url != null) {
                    ArticleWebViewScreen(url)
                }
            }
        }
    }
}