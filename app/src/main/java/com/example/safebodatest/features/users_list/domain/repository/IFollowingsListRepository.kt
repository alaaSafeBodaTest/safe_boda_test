package com.example.safebodatest.features.users_list.domain.repository

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.features.users_list.domain.entity.FollowingListItemEntity

interface IFollowingsListRepository {

    suspend fun getFollowingsList() : Either<IFailure, List<FollowingListItemEntity>>

}