package com.example.vrid_blog_application

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import android.net.Uri


@Composable
fun BlogApp() {
    val navController = rememberNavController()
    val blogViewModel: BlogViewModel = viewModel()

    NavHost(navController = navController, startDestination = "list") {
        // Blog List Screen
        composable("list") {
            BlogListScreen(navController, blogViewModel)
        }

        // Blog Detail Screen (WebView)
        composable("detail/{url}") { entry ->
            val blogUrl = entry.arguments?.getString("url") ?: ""
            BlogDetailScreen(navController, Uri.decode(blogUrl))  // Decoding URL
        }
    }
}
