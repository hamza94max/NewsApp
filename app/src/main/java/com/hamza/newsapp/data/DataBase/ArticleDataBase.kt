package com.hamza.newsapp.data.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hamza.newsapp.data.Dao.ArticleDao
import com.hamza.newsapp.data.Model.Article


@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class ArticleDataBase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object {
        @Volatile
        private var instance: ArticleDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDataBase::class.java,
                "Article_dp"
            )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()


                .build()

    }
}