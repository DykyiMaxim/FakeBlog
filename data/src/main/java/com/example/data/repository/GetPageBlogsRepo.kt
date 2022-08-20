package com.example.data.repository

import com.example.common.Resource
import com.example.data.mappers.toDomain
import com.example.data.network.ApiService
import com.example.domain.models.Blog
import com.example.domain.repository.GetPageRepo
import javax.inject.Inject

class GetPageBlogsRepo @Inject constructor(private val apiService: ApiService) :
    GetPageRepo {

    override suspend fun getPageBlog(page: Int, limit: Int): Resource<List<Blog>> {
        return try {
            val response = apiService.getBlogsPagination(page = page, limit = limit)
            if (response.isSuccessful) {
                val body = response.body()?.data?.toDomain()
                Resource.Success(body)

            } else {
                Resource.Error(response.errorBody()?.string())
            }

        } catch (e: Exception) {
            Resource.Error(e.localizedMessage)
        }
    }
}