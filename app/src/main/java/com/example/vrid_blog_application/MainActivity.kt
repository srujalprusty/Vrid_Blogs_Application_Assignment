package com.example.vrid_blog_application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.vrid_blog_application.ui.theme.Vrid_Blog_ApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Vrid_Blog_ApplicationTheme {
                BlogApp()
            }
        }
    }
}

