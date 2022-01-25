package com.example.newsapp.domain.useCase.model

import com.example.newsapp.domain.useCase.deleteArticleUseCase.DeleteArticleUseCase
import com.example.newsapp.domain.useCase.getSavedNewsUseCase.GetSavedNewsUseCase
import com.example.newsapp.domain.useCase.saveArticleUseCase.SaveArticleUseCase

data class NewsDbUseCases(
    val getSavedNewsUseCase: GetSavedNewsUseCase,
    val saveArticleUseCase: SaveArticleUseCase,
    val deleteArticleUseCase: DeleteArticleUseCase,
)