package com.example.newsapp.presentation.common

import com.example.newsapp.domain.useCase.getEverythingUseCase.GetEverythingUseCase
import com.example.newsapp.domain.useCase.getHeadlinesUseCase.GetHeadlinesUseCase

data class NewsUseCases(
    val getEverythingUseCase: GetEverythingUseCase,
    val getHeadlinesUseCase: GetHeadlinesUseCase
)
