package com.hamza.newsapp.data.RemoteData

import com.hamza.newsapp.data.Model.NewsResponse
import com.hamza.newsapp.util.Contants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/articles")
    suspend fun getTeslaNews(

        @Query("sourceName")
        sourceName: String

    ): NewsResponse


    @GET("v2/everything")
    suspend fun searchForNews(

        @Query("q")
        searchQuery: String = "tesla",

        @Query("apiKey")
        apiKey: String = API_KEY

    ): Response<NewsResponse>
}