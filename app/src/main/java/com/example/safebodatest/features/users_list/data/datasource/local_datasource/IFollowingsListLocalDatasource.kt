package com.example.safebodatest.features.users_list.data.datasource.local_datasource

import arrow.core.Either
import com.example.safebodatest.core.db.tables.Following
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.features.users_list.data.model.FollowingListItemModel

interface IFollowingsListLocalDatasource {

    suspend fun storeFollowingsListLocally(list: List<FollowingListItemModel>): Either<IFailure, List<Long>>
    fun logout(): Either<IFailure, Any>
    suspend fun getFollowingsList(page: Int = 1): Either<IFailure, List<FollowingListItemModel>>

}