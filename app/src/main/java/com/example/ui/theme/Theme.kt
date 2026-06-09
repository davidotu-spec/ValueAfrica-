package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Terracotta,
    secondary = LeafTeal,
    tertiary = SunOchre,
    background = DarkSlateCharcoal,
    surface = DeepForestSurface,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.Black,
    onBackground = TextLight,
    onSurface = TextLight,
    surfaceVariant = Color(0xFF432C1D),       // Deep coffee/espresso surface variant
    onSurfaceVariant = Color(0xFFE6CCB2)      // Light latte
)

private val LightColorScheme = lightColorScheme(
    primary = Terracotta,
    secondary = LeafTeal,
    tertiary = SunOchre,
    background = WarmCreamLight,
    surface = ClaySurfaceLight,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.Black,
    onBackground = TextDark,
    onSurface = TextDark,
    surfaceVariant = Color(0xFFF4EBE2),       // Beautiful sand oatmeal / alabaster
    onSurfaceVariant = Color(0xFF432C1D)      // Espresso dark brown text
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
