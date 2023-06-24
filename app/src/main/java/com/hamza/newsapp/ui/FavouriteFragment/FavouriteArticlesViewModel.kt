package com.hamza.newsapp.ui.FavouriteFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hamza.newsapp.data.Model.Article
import com.hamza.newsapp.data.Repository.FavArticlesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteArticlesViewModel @Inject constructor(application: Application) :
    AndroidViewModel(application) {

    private var repository: FavArticlesRepository = FavArticlesRepository(application)
    private var allNews: LiveData<List<Article?>>? = repository.getAllArticles()

    fun getFavNews() = allNews

    fun insertArticle(article: Article) = viewModelScope.launch {
        repository.insert(article)
    }

    fun deleteAllFavArticles() = viewModelScope.launch {
        repository.deleteAllFavArticles()
    }


}