package com.example.safebodatest.core.api

import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.features.users_list.data.model.FollowingListItemModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface for retrofit apis
 * */
interface APIs {

    @GET("/")
    suspend fun testApi(): Response<Any>

    @GET(URLs.USER)
    suspend fun getUser(): Response<User>

    @GET(URLs.FOLLOWINGS)
    suspend fun getFollowers(@Query("page") page: Int = 1,@Query("per_page") per_page: Int = 8): Response<List<FollowingListItemModel>>

}