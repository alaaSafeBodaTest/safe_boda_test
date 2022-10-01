package com.example.safebodatest.features.users_list.data.datasource.remote_datasource

import arrow.core.Either
import com.example.safebodatest.core.api.HttpHandler
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.features.users_list.data.model.FollowingListItemModel

interface IFollowingsListRemoteDatasource: HttpHandler {

    suspend fun getFollowingsList() : Either<IFailure, List<FollowingListItemModel>>

}