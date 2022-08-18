package com.example.fakeblog.presentation.Home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Resource
import com.example.domain.use_cases.GetBlogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBlogsUseCase:GetBlogsUseCase
) :ViewModel() {
    private val blogs = mutableStateOf<HomeState>(HomeState())
    val BlogsState:State<HomeState> = blogs
    init {
        getBlogs()
    }

    fun getBlogs(){
        getBlogsUseCase().onEach {
            when(it){
                is Resource.Loading->{
                    blogs.value = HomeState(isLoading = true)
                }
                is Resource.Success->{
                    blogs.value = HomeState(data = it.data)
                }
                is Resource.Error->{
                    blogs.value = HomeState(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

}