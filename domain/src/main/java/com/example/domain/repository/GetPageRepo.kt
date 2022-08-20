package com.example.domain.repository

import com.example.common.Resource
import com.example.domain.models.Blog

interface GetPageRepo {
    suspend fun getPageBlog(page:Int,limit:Int):Resource<List<Blog>>

}
