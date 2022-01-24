package com.example.newsapp.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.domain.useCase.getHeadlinesUseCase.GetHeadlinesUseCase
import javax.inject.Inject

class NewsViewModelFactory @Inject constructor(private val newsUseCases: NewsUseCases) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(newsUseCases) as T
    }
}