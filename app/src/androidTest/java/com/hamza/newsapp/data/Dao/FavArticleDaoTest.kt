package com.hamza.newsapp.data.Dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.hamza.newsapp.data.DataBase.FavArticlesDataBase
import com.hamza.newsapp.data.Model.Article
import com.hamza.newsapp.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class FavArticleDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: FavArticlesDataBase
    private lateinit var dao: FavArticleDao


    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            FavArticlesDataBase::class.java
        ).allowMainThreadQueries().build()


        dao = database.getArticleDao()
    }
    
    @Test
    fun insertArticle() = runBlockingTest {
        val article = Article(
            16, "author", "content", "des", "at ", "title", "url", "toimg"
        )

        dao.insertArticle(article)

        val allArticles = dao.getAllArticles()?.getOrAwaitValue()

        assertThat(allArticles?.contains(article)).isTrue()
    }


    @After
    fun teardown() {
        database.close()
    }


}