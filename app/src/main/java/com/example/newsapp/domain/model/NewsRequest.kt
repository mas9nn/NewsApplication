package com.example.newsapp.domain.model

data class NewsRequest(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)