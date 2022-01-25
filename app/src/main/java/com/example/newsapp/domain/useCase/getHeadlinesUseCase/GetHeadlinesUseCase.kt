package com.example.newsapp.domain.useCase.getHeadlinesUseCase

import com.example.newsapp.data.remote.interceptors.NoInternetException
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.util.Resource
import com.example.newsapp.util.checkResponse
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHeadlinesUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    operator fun invoke(category: String, page: Int) = flow {
        emit(Resource.Loading())
        try {
            val request = newsRepository.getTopHeadlines(category, page)
//            var savedArticles = listOf<Article>()
//
//            val checkedRequest = request.checkResponse()
//            if (checkedRequest is Resource.Success && savedArticles.isNotEmpty()) {
//                emit(Resource.Success(checkedRequest.data?.copy(articles = checkedRequest.data.articles.map {
//                    it.copy(saved = savedArticles.contains(it))
//                })))
//            } else {
//                emit(checkedRequest)
//            }
            emit(request.checkResponse())
        } catch (e: NoInternetException) {
            emit(Resource.NoInternet())
        }
    }

}