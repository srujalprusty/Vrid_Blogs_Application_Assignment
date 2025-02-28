package com.example.vrid_blog_application.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Define Beautiful Colors
val BeautifulBlue = Color(0xFF1E3A8A) // Deep Blue for Background
val BeautifulPurple = Color(0xFF7E57C2) // Vibrant Purple for Cards
val BeautifulLightPurple = Color(0xFFD1C4E9) // Soft Purple for Surface

// Dark Mode Color Scheme
private val DarkColorScheme = darkColorScheme(
    primary = BeautifulPurple,
    secondary = BeautifulLightPurple,
    background = Color.Black,
    surface = Color.DarkGray,
    onPrimary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White
)

// Light Mode Color Scheme (Updated with Beautiful Colors)
private val LightColorScheme = lightColorScheme(
    primary = BeautifulPurple,
    secondary = BeautifulLightPurple,
    background = BeautifulBlue,  // Set deep blue background
    surface = BeautifulLightPurple, // Use soft purple for card backgrounds
    onPrimary = Color.White,
    onBackground = Color.White,
    onSurface = Color.Black
)

@Composable
fun Vrid_Blog_ApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
