package com.example.newsapp.domain.useCase.model

import com.example.newsapp.domain.useCase.deleteArticleUseCase.DeleteArticleUseCase
import com.example.newsapp.domain.useCase.getEverythingUseCase.GetEverythingUseCase
import com.example.newsapp.domain.useCase.getHeadlinesUseCase.GetHeadlinesUseCase
import com.example.newsapp.domain.useCase.getSavedNewsUseCase.GetSavedNewsUseCase
import com.example.newsapp.domain.useCase.saveArticleUseCase.SaveArticleUseCase

data class NewsUseCases(
    val getEverythingUseCase: GetEverythingUseCase,
    val getHeadlinesUseCase: GetHeadlinesUseCase,
    val newsDbUseCases: NewsDbUseCases
)
