package com.example.newsapp.domain.repository

import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.model.NewsRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface NewsRepository {

    suspend fun getTopHeadlines(category: String, page: Int): Response<NewsRequest>

    suspend fun getEverything(query: String, page: Int): Response<NewsRequest>

    suspend fun insertArticle(article: Article)

    fun getSavedNews(): Flow<List<Article>>

    suspend fun deleteArticle(article: Article)

}

