package com.hamza.newsapp.data.Repository

import android.app.Application
import com.hamza.newsapp.data.Dao.ArticleDao
import com.hamza.newsapp.data.DataBase.ArticleDataBase.Companion.invoke
import com.hamza.newsapp.data.Model.Article

class ArticleRepository(application: Application?) {


    private val dao: ArticleDao by lazy {
        val database = invoke(application!!)
        database.getArticleDao()
    }


    fun getAllArticles() = dao.getAllArticles()

    suspend fun insert(article: Article) {
        dao.insertArticle(article)
    }


}