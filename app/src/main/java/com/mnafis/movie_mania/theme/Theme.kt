package com.mnafis.movie_mania.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimaryColor, // button
    onPrimary = ColorWhite, // button text
    primaryContainer = DarkPrimaryColor, // fab background
    onPrimaryContainer = ColorWhite, // fab text
    background = DarkBackground, // background
    onBackground = ColorWhite, // regular text
    surface = DarkPrimaryColor, // top app bar
    onSurface = ColorWhite, // top app bar text + search bar text(overridden by the search bar direct specification)
    surfaceVariant = DarkCardBackground, // card background
    onSurfaceVariant = ColorOffWhite, // card text
)

private val LightColorScheme = lightColorScheme(
    primary = LightPrimaryColor, // button
    onPrimary = ColorWhite, // button text
    primaryContainer = LightPrimaryDarkColor, // fab background
    onPrimaryContainer = ColorWhite, // fab text
    background = ColorWhite, // background
    onBackground = ColorBlack, // regular text
    surface = LightPrimaryColor, // top app bar
    onSurface = ColorWhite, // top app bar text + search bar text(overridden by the search bar direct specification)
    surfaceVariant = ColorOffWhite, // card background
    onSurfaceVariant = ColorGray, // card text
)

@Composable
fun MovieManiaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) = MaterialTheme(
    colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
    typography = Typography,
    content = content
)