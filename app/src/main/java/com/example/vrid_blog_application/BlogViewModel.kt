package com.example.vrid_blog_application

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vrid_blog_application.Data.BlogPost
import com.example.vrid_blog_application.Data.RetrofitInstance
import kotlinx.coroutines.launch
import androidx.compose.runtime.State


class BlogViewModel : ViewModel() {
    private val _blogs = mutableStateOf<List<BlogPost>>(emptyList())
    val blogs: State<List<BlogPost>> = _blogs

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    init {
        fetchBlogs()
    }

    fun fetchBlogs() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _blogs.value = RetrofitInstance.api.getBlogPosts()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
