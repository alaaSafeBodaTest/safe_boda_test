package com.example.safebodatest.core.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Retrofit Generator Class
 * */
object ServiceGenerator {

    /**
     * Client to configure http connection
     * */
    private val okHttpClient = OkHttpClient
        .Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .build()

    /**
     * Retrofit Instance
     * */
    val api: APIs = Retrofit
        .Builder()
        .baseUrl(URLs.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(APIs::class.java)

}