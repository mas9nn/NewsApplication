package com.example.newsapp.domain.useCase.getHeadlinesUseCase

import com.example.newsapp.data.remote.interceptors.NoInternetException
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.util.Resource
import com.example.newsapp.util.checkResponse
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHeadlinesUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    operator fun invoke(category: String,page:Int) = flow {
        emit(Resource.Loading())
        try {
            val user = newsRepository.getTopHeadlines(category,page)
            emit(user.checkResponse())
        } catch (e: NoInternetException) {
            emit(Resource.NoInternet())
        }
    }


}