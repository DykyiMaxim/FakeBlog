package com.example.domain.use_cases

import com.example.domain.models.Blog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.common.Resource
import com.example.domain.repository.GetBlogsRepository
import java.lang.Exception
import javax.inject.Inject

class GetBlogsUseCase @Inject constructor(private val getBlogsRepository: GetBlogsRepository) {
    suspend operator fun invoke(): Flow<Resource<List<Blog>>> = flow{
        emit(Resource.Loading(true))
        try{
            val response = getBlogsRepository.getBlogs()
            emit(Resource.Success(data = response))

        }catch (e:Exception){
            emit(Resource.Error("Cant Load Blogs"))
        }
        emit(Resource.Loading(false))
    }
}