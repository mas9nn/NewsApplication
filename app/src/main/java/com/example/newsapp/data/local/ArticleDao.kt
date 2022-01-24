package com.example.newsapp.data.local

import androidx.room.*
import com.example.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article)

    @Query("SELECT * FROM articles")
    fun getAllArticles(): Flow<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}