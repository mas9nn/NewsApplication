package com.example.newsapp.presentation.savedNewsScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.useCase.model.NewsDbUseCases
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject

class SavedNewsViewModel @Inject constructor(
    private val savedNewsDbUseCases: NewsDbUseCases
) : ViewModel() {

    private val _state: MutableLiveData<SavedNewsState> = MutableLiveData(SavedNewsState())
    val state: LiveData<SavedNewsState> = _state


    private var lastDeletedArticle: Article? = null

    init {
        getSavedNews()
    }

    fun saveArticleToDb() {
        lastDeletedArticle?.let {
            savedNewsDbUseCases.saveArticleUseCase.invoke(it)
                .onCompletion { lastDeletedArticle = null }.launchIn(viewModelScope)
        }
    }

    fun deleteArticleFromDb(article: Article) {
        savedNewsDbUseCases.deleteArticleUseCase.invoke(article)
            .onCompletion { lastDeletedArticle = article }.launchIn(viewModelScope)
    }

    fun getSavedNews() {
        viewModelScope.launch {
            savedNewsDbUseCases.getSavedNewsUseCase.invoke().collectLatest {
                if (it.isEmpty()) {
                    _state.value = SavedNewsState(noContent = true)
                } else {
                    _state.value = SavedNewsState(articles = it)
                }
            }
        }
    }

}

data class SavedNewsState(
    val loading: Boolean = false,
    val noContent: Boolean = false,
    val articles: List<Article>? = null
)