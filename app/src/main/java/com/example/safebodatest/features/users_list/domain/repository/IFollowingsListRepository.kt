package com.example.safebodatest.features.users_list.domain.repository

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.features.users_list.domain.entity.FollowingListItemEntity

interface IFollowingsListRepository {

    suspend fun getFollowingsList(page: Int = 1) : Either<IFailure, List<FollowingListItemEntity>>

    suspend fun storeFollowingsList(list: List<FollowingListItemEntity>): Either<IFailure, List<Long>>
    fun logout(): Either<IFailure, Any>

}