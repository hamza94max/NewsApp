package com.hamza.newsapp.data.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
)

@Entity(tableName = "Articles")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val ArticleId: Int? = null,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String,

    @SerializedName("isFav")
    var isFav: Int = 0
) : Serializable

data class Source(
    val id: Any,
    val name: String
)
