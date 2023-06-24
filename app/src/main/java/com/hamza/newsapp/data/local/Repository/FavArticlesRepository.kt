package com.hamza.newsapp.data.Repository

import android.app.Application
import com.hamza.newsapp.data.Dao.FavArticleDao
import com.hamza.newsapp.data.DataBase.FavArticlesDataBase.Companion.invoke
import com.hamza.newsapp.data.Model.Article

class FavArticlesRepository(application: Application?) {


    private val dao: FavArticleDao by lazy {
        val database = invoke(application!!)
        database.getArticleDao()
    }


    fun getAllArticles() = dao.getAllArticles()

    suspend fun insert(article: Article) {
        dao.insertArticle(article)
    }

    suspend fun deleteAllFavArticles() {
        dao.deleteAllFavArticles()
    }


}