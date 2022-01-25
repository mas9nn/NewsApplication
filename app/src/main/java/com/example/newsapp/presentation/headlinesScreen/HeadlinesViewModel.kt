package com.example.newsapp.presentation.headlinesScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.useCase.model.NewsUseCases
import com.example.newsapp.presentation.common.NewsState
import com.example.newsapp.util.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach


class HeadlinesViewModel(
    private val newsUseCases: NewsUseCases,
    private val searchQuery: String
) : ViewModel() {

    private val _state: MutableLiveData<NewsState> = MutableLiveData(NewsState())
    val state: LiveData<NewsState> = _state

    init {
        getHeadlines(1)
    }


    fun getHeadlines(page: Int) {
        newsUseCases.getHeadlinesUseCase.invoke(searchQuery, page).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    if (result.data?.totalResults?.coerceAtLeast(0) == 0) {
                        _state.value = NewsState(noContent = searchQuery)
                    } else {
                        val fullList = _state.value?.result
                        _state.value = _state.value?.copy(
                            loading = false,
                            result = fullList?.apply {
                                addAll(result.data?.articles ?: listOf())
                            })
                    }
                }
                is Resource.Loading -> {
                    _state.value = _state.value?.copy(loading = true, isFirstPage = page == 1)
                }
                else -> {
                    _state.value =
                        _state.value?.copy(loading = false, error = true, isLastPage = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun saveArticleToDb(article: Article) {
        newsUseCases.newsDbUseCases.saveArticleUseCase.invoke(article).onCompletion {
        }.launchIn(viewModelScope)
    }

    fun deleteArticleFromDb(article: Article) {
        newsUseCases.newsDbUseCases.deleteArticleUseCase.invoke(article).onCompletion {
        }.launchIn(viewModelScope)
    }

    fun clearData() {
        _state.value = NewsState()
    }
}