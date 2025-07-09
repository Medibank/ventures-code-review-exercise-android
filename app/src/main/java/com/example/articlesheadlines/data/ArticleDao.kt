package com.example.articlesheadlines.data

import androidx.room.*
import com.example.articlesheadlines.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Query("SELECT * FROM saved_articles")
    fun getAll(): Flow<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Delete
    suspend fun delete(article: Article)

    @Query("SELECT EXISTS(SELECT 1 FROM saved_articles WHERE url = :url)")
    suspend fun isSaved(url: String): Boolean
}