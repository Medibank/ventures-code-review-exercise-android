package com.example.newsapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF774F83),
    secondary = Color(0xFF6A596C),
    tertiary = Color(0xFF82524F)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFE6B6F1),
    secondary = Color(0xFFD5C0D7),
    tertiary = Color(0xFF4C2523)
)

@Composable
fun NewsComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = darkColorScheme(),
        typography = Typography,
        content = content
    )
}