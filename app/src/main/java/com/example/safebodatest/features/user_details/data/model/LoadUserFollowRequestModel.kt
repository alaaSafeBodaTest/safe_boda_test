package com.example.safebodatest.features.user_details.data.model

import com.example.safebodatest.features.user_details.domain.entity.LoadUserFollowRequestEntity

class LoadUserFollowRequestModel(page: Int, username: String): LoadUserFollowRequestEntity(page = page, username = username)