package com.example.safebodatest.features.user_details.data.datasource.remote_datasource

import arrow.core.Either
import com.example.safebodatest.core.api.ServiceGenerator
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.failures.RemoteFailure
import javax.inject.Inject

class UserDetailsRemoteDatasourceImpl @Inject constructor() : IUserDetailsRemoteDatasource {

    override suspend fun loadUserByUsername(username: String): Either<IFailure, User> {
        return handleRequest {
            val response = ServiceGenerator.api.getUserByUsername(username)
            return@handleRequest if (response.isSuccessful) {
                val user = response.body()
                if (user != null)
                    Either.Right(user)
                else
                    Either.Left(RemoteFailure("Unknown Failure", 200))
            } else {
                Either.Left(RemoteFailure(response.message(), response.code()))
            }
        }
    }
}