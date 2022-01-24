package com.example.newsapp.domain.useCase.getSavedNewsUseCase

import com.example.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetSavedNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    operator fun invoke() = newsRepository.getSavedNews()

}