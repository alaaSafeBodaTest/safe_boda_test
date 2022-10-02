package com.example.safebodatest.features.user_details.domain.entity

open class LoadUserFollowRequestEntity(
    val username: String,
    val page: Int = 1,
)