package com.example.fakeblog.presentation.Home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.common.Resource
import com.example.data.local.BlogDao
import com.example.data.paging.BlogRemoteMediator
import com.example.data.repository.GetPageBlogsRepo
import com.example.domain.repository.GetPageRepo
import com.example.domain.use_cases.GetBlogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBlogsUseCase:GetBlogsUseCase,
    private val getPagerRepo:GetPageRepo,
    private val blogDAO: BlogDao
) :ViewModel() {
    @OptIn(ExperimentalPagingApi::class)
    val pager = Pager(
        config = PagingConfig(pageSize = 10, prefetchDistance = 5),
        remoteMediator = BlogRemoteMediator(
            getPagerRepo = getPagerRepo,
            blogDAO = blogDAO
        )
    ) {
        blogDAO.GetAllBlogsItems()
    }.flow.cachedIn(viewModelScope)





//    private val blogs = mutableStateOf<HomeState>(HomeState())
//    val BlogsState:State<HomeState> = blogs
//    init {
//        getBlogs()
//    }
//
//    fun getBlogs(){
//        getBlogsUseCase().onEach {
//            when(it){
//                is Resource.Loading->{
//                    blogs.value = HomeState(isLoading = true)
//                }
//                is Resource.Success->{
//                    blogs.value = HomeState(data = it.data)
//                }
//                is Resource.Error->{
//                    blogs.value = HomeState(error = it.message.toString())
//                }
//            }
//        }.launchIn(viewModelScope)
//    }

}