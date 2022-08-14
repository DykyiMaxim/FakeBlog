package com.example.domain.repository

import com.example.domain.models.Blog
import retrofit2.Response

interface BlogsRepository {
    suspend fun getBlogs():Response<List<Blog>>
}