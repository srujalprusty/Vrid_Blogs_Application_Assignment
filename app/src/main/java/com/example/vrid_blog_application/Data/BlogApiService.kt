package com.example.vrid_blog_application.Data

import retrofit2.http.GET



interface BlogApiService {
        @GET("wp-json/wp/v2/posts?per_page=10&page=1")
        suspend fun getBlogPosts(): List<BlogPost>

}