package com.hamza.newsapp.ui.Fragments.FaouriteFragment


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hamza.newsapp.data.Model.Article
import com.hamza.newsapp.data.Repository.ArticleRepository
import com.hamza.newsapp.util.Resource
import kotlinx.coroutines.launch

class FavouriteArticlesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ArticleRepository = ArticleRepository(application)
    private var articleNews = MutableLiveData<Resource<Article>>()

    fun getAllArticles() = articleNews as LiveData<Resource<Article>>

    fun insertArticle(article: Article) = viewModelScope.launch {
        repository.insert(article)
    }


}