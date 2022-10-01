package com.example.safebodatest.features.users_list.data.datasource.local_datasource

import arrow.core.Either
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.features.users_list.data.model.FollowingListItemModel

interface IFollowingsListLocalDatasource {

    suspend fun storeFollowingsListLocally(list: List<FollowingListItemModel>): Either<IFailure, List<Long>>

}