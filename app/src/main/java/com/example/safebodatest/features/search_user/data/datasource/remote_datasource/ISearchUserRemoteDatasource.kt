package com.example.safebodatest.features.search_user.data.datasource.remote_datasource

import arrow.core.Either
import com.example.safebodatest.core.api.HttpHandler
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure

interface ISearchUserRemoteDatasource: HttpHandler {

    suspend fun searchUser(username: String): Either<IFailure, User>

}