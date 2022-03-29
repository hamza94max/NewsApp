package com.hamza.newsapp.data.RemoteData

import com.hamza.newsapp.util.Contants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RerofitInstance {


    companion object {
        private val retrofit by lazy {

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .build()
        }

        val api by lazy {
            retrofit.create(NewsApi::class.java)
        }
    }
}