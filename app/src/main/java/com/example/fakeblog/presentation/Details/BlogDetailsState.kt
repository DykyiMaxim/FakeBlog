package com.example.fakeblog.presentation.Details

import com.example.domain.models.Blog

data class BlogDetailsState (
    val isLoading: Boolean = false,
    val data: Blog? = null,
    val error: String = "")