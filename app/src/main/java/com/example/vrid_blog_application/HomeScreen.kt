package com.example.vrid_blog_application

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.vrid_blog_application.Data.BlogPost
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun BlogListScreen(navController: NavController, viewModel: BlogViewModel) {
    val blogs by viewModel.blogs
    val isLoading by viewModel.isLoading

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)
Surface(
    modifier = Modifier.fillMaxSize(),
    color = Color.White
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Vrid Blogs", fontFamily = FontFamily.Monospace,
            fontSize = 30.sp, fontWeight = FontWeight.SemiBold
        ) },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
        ) }
    ) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { viewModel.fetchBlogs() } // Refresh blogs on swipe
        ) {
            Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)


            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    LazyColumn(modifier = Modifier.padding(it)) {
                        items(blogs) { blog ->
                            BlogItem(blog, navController)
                        }
                    }
                }
            }
        }
    }
}
}

@Composable
fun BlogItem(blog: BlogPost, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                val encodedUrl = Uri.encode(blog.link)
                navController.navigate("detail/$encodedUrl")
            },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            blog.jetpack_featured_media_url?.let { imageUrl ->
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = "Blog Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            Text(text = blog.title.rendered, style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview(){
    BlogListScreen(navController = rememberNavController(), viewModel = BlogViewModel())
}
