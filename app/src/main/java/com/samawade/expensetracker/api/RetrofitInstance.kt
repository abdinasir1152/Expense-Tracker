package com.samawade.expensetracker.api

import com.samawade.expensetracker.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    val api: RemoteApiServices by lazy {
        retrofit.create(RemoteApiServices::class.java)
    }
}