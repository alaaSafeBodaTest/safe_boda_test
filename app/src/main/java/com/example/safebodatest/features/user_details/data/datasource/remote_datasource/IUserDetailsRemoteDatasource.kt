package com.example.safebodatest.features.user_details.data.datasource.remote_datasource

import arrow.core.Either
import com.example.safebodatest.core.api.HttpHandler
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure

interface IUserDetailsRemoteDatasource : HttpHandler {

    suspend fun loadUserByUsername(username: String): Either<IFailure, User>
    suspend fun loadUserFollowingsByUsername(username: String): Either<IFailure, List<User>>
    suspend fun loadUserFollowersByUsername(username: String): Either<IFailure, List<User>>

}