package com.example.domain.repository

import com.example.domain.models.Blog
import retrofit2.Response

interface GetBlogsRepository {
    suspend fun getBlogs():List<Blog>
}