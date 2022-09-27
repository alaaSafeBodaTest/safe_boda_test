package com.example.safebodatest.core.api

import retrofit2.Response
import retrofit2.http.GET

/**
 * Interface for retrofit apis
 * */
interface APIs {

    @GET("/")
    suspend fun testApi(): Response<Any>

}