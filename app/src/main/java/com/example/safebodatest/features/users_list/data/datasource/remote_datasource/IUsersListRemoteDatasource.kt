package com.example.safebodatest.features.users_list.data.datasource.remote_datasource

import arrow.core.Either
import com.example.safebodatest.core.api.HttpHandler
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure

interface IUsersListRemoteDatasource: HttpHandler {

    suspend fun getUsersList() : Either<IFailure, List<User>>

}