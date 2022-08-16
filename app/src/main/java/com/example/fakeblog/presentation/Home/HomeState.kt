package com.example.fakeblog.presentation.Home

import com.example.domain.models.Blog

data class HomeState (
    var isLoading: Boolean= false,
    var data:List<Blog>?=null,
    var error:String = ""
)