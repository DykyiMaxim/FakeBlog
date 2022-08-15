package com.example.data.repository

import com.example.data.mappers.toDomain
import com.example.data.network.ApiService
import com.example.domain.models.Blog
import com.example.domain.repository.GetBlogsRepository
import com.example.data.network.utils.SafeApiRequest
import javax.inject.Inject

class GetBlogRepositoryImpl @Inject constructor(private val apiService:ApiService) :SafeApiRequest(),GetBlogsRepository {
    override suspend fun getBlogs(): List<Blog> {
        val response = safeApiRequest { apiService.getBlogs() }
        return response.data?.toDomain()?: emptyList()
    }

}