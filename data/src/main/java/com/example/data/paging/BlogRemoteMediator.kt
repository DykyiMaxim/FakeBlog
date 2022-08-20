package com.example.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.common.Resource
import com.example.data.local.BlogDBkey
import com.example.data.local.BlogDao
import com.example.data.repository.GetPageBlogsRepo
import com.example.domain.models.Blog
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class BlogRemoteMediator @Inject constructor(
    private val initialPage: Int = 1,
    private val getPagerBlogsRepo: GetPageBlogsRepo,
    private val blogDAO:BlogDao
) : RemoteMediator<Int, Blog>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Blog>): MediatorResult {

        return try {

            val page: Int = when (loadType) {
                LoadType.APPEND -> {
                    val remoteKeys = getLastKey(state)
                    remoteKeys?.next ?: return MediatorResult.Success(true)
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(true)
                }
                LoadType.REFRESH -> {
                    val remoteKeys = getClosetKey(state)
                    remoteKeys?.next?.minus(1) ?: initialPage

                }
            }

            val response =
                getPagerBlogsRepo.getPageBlog(page = page, limit = state.config.pageSize)
            val endOfPagination = response.data?.size!! < state.config.pageSize

            when (response) {
                is Resource.Success -> {
                    val body = response.data

                    if (loadType == LoadType.REFRESH) {
                        blogDAO.deleteAllBlogsKeys()
                        blogDAO.deleteAllItems()
                    }

                    val prev = if (page == initialPage) initialPage else page - 1
                    val next = if (endOfPagination) null else page + 1

                    val list = body?.map {
                        BlogDBkey(id = it.id, prev, next)
                    }
                    list?.let { blogDAO.insertAllBlogsKeys(list) }
                    body?.let { blogDAO.insertAllBlogs(body) }


                }
                is Resource.Error -> {
                    MediatorResult.Error(Exception())

                }

            }

            if (response is Resource.Success) {
                if (endOfPagination) {
                    MediatorResult.Success(true)

                } else {
                    MediatorResult.Success(false)
                }
            } else {
                MediatorResult.Success(true)
            }


        } catch (E: Exception) {

            MediatorResult.Error(E)

        }
    }

    suspend fun getLastKey(state: PagingState<Int, Blog>): BlogDBkey? {
        return state.lastItemOrNull()?.let {
            blogDAO.getAllKeys(it.id)
        }
    }

    suspend fun getClosetKey(state: PagingState<Int, Blog>): BlogDBkey? {
        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.let {
                blogDAO.getAllKeys(it.id)
            }
        }
    }

}
