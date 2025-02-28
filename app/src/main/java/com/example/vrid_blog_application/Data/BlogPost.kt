package com.example.vrid_blog_application.Data


data class BlogPost(
    val id: Int,
    val title: BlogTitle,
    val link: String,
    val jetpack_featured_media_url: String?
)

data class BlogTitle(
    val rendered: String
)
