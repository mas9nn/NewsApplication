package com.example.newsapp.data.repository

import com.example.newsapp.data.local.ArticleDao
import com.example.newsapp.data.remote.Api
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.model.NewsRequest
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: Api,
    private val articleDao: ArticleDao
) :
    NewsRepository {
    override suspend fun getTopHeadlines(category: String,page:Int): Response<NewsRequest> {
        return api.getTopHeadlines(category,page)
    }

    override suspend fun getEverything(query: String,page:Int): Response<NewsRequest> {
        return api.getEverything(query,page)
    }

    override suspend fun insertArticle(article: Article) {
        articleDao.insertArticle(article)
    }

    override fun getSavedNews(): Flow<List<Article>> {
        return articleDao.getAllArticles()
    }

    override suspend fun deleteArticle(article: Article) {
        articleDao.deleteArticle(article)
    }
}

