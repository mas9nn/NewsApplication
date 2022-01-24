package com.example.newsapp.data.remote

import com.example.newsapp.domain.model.NewsRequest
import com.example.newsapp.util.Constants.Companion.API_KEY
import com.example.newsapp.util.Constants.Companion.PAGE_SIZE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("category")
        category: String,
        @Query("page")
        page: Int,
        @Query("page_size")
        page_size: Int = PAGE_SIZE,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsRequest>

    @GET("v2/everything")
    suspend fun getEverything(
        @Query("q")
        searchQuery: String,
        @Query("page")
        page: Int,
        @Query("page_size")
        page_size: Int = PAGE_SIZE,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsRequest>

}