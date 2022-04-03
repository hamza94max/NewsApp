package com.hamza.newsapp.data.RemoteData

import com.hamza.newsapp.BuildConfig
import com.hamza.newsapp.data.Model.NewsResponse
import com.hamza.newsapp.util.Contants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getTeslaNews(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>


    @GET("v2/everything")
    suspend fun searchForNews(

        @Query("q")
        searchQuery: String = "tesla",

        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY

    ): Response<NewsResponse>
}