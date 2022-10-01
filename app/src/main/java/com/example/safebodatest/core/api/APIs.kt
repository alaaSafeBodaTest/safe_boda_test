package com.example.safebodatest.core.api

import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.features.users_list.data.model.FollowingListItemModel
import retrofit2.Response
import retrofit2.http.GET

/**
 * Interface for retrofit apis
 * */
interface APIs {

    @GET("/")
    suspend fun testApi(): Response<Any>

    @GET(URLs.USER)
    suspend fun getUser(): Response<User>

    @GET(URLs.FOLLOWINGS)
    suspend fun getFollowers(): Response<List<FollowingListItemModel>>

}