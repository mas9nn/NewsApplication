package com.example.newsapp.di

import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.domain.useCase.deleteArticleUseCase.DeleteArticleUseCase
import com.example.newsapp.domain.useCase.getEverythingUseCase.GetEverythingUseCase
import com.example.newsapp.domain.useCase.getHeadlinesUseCase.GetHeadlinesUseCase
import com.example.newsapp.domain.useCase.getSavedNewsUseCase.GetSavedNewsUseCase
import com.example.newsapp.domain.useCase.saveArticleUseCase.SaveArticleUseCase
import com.example.newsapp.presentation.common.NewsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    @Singleton
    fun provideNewsUseCase(repository: NewsRepository): NewsUseCases {
        return NewsUseCases(
            getEverythingUseCase = GetEverythingUseCase(newsRepository = repository),
            getHeadlinesUseCase = GetHeadlinesUseCase(newsRepository = repository),
            getSavedNewsUseCase = GetSavedNewsUseCase(newsRepository = repository),
            saveArticleUseCase = SaveArticleUseCase(newsRepository = repository),
            deleteArticleUseCase = DeleteArticleUseCase(newsRepository = repository)
        )
    }

}