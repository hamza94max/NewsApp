package com.hamza.newsapp.data.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hamza.newsapp.data.Model.Article

@Dao
interface FavArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticlesList(article: List<Article>): List<Long>

    @Delete
    suspend fun deleteArticle(article: Article)

    @Query("SELECT * FROM Articles")
    fun getAllArticles(): LiveData<List<Article?>>?

    @Query("DELETE FROM Articles")
    suspend fun deleteAllFavArticles()


}