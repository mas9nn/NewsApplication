package com.example.newsapp.domain.useCase.deleteArticleUseCase

import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteArticleUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    operator fun invoke(article: Article) = flow { emit(newsRepository.deleteArticle(article)) }

}