package com.example.newsapp.presentation.common

import com.example.newsapp.domain.model.NewsRequest

data class NewsState(
    val loading: Boolean = false,
    val error: Boolean = false,
    val isLastPage:Boolean = false,
    val isFirstPage:Boolean = false,
    val noContent: String = "",
    val result: NewsRequest? = null
)