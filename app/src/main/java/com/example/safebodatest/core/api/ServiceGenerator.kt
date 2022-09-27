package com.example.safebodatest.core.api

import com.example.safebodatest.core.consts.Keys
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Retrofit Generator Class
 * */
object ServiceGenerator {

    var token: String = ""

    /**
     * Build request headers
     * */
    private val headerInterceptor: Interceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()
            val request = original
                .newBuilder()
                .addHeader(Keys.TOKEN, token)
                .addHeader(Keys.ACCEPT, "application/vnd.github+json")
                .method(original.method, original.body)
                .build()
            return chain.proceed(request)
        }
    }

    /**
     * Http Logger
     * */
    private val logger: HttpLoggingInterceptor
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.apply { loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY }
            return loggingInterceptor
        }

    /**
     * Client to configure http connection
     * */
    private val okHttpClient = OkHttpClient
        .Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .addInterceptor(headerInterceptor)
        .addInterceptor(logger)
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