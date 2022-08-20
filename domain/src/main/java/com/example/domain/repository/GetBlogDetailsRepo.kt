package com.example.domain.repository

import com.example.domain.models.Blog

interface GetBlogDetailsRepo {
    suspend fun getBlogDetails(id:String): Blog
}