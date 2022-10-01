package com.example.safebodatest.features.users_list.data.datasource.remote_datasource

import arrow.core.Either
import com.example.safebodatest.core.api.ServiceGenerator
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.failures.RemoteFailure
import javax.inject.Inject

class UsersListRemoteDatasourceImpl @Inject constructor(): IUsersListRemoteDatasource {

    override suspend fun getUsersList(): Either<IFailure, List<User>> {
        return handleRequest {
            val response = ServiceGenerator.api.getFollowers()
            return@handleRequest if (response.isSuccessful) {
                val followers = response.body()
                if (followers != null)
                    Either.Right(followers)
                else
                    Either.Left(RemoteFailure("", 200))
            } else {
                Either.Left(RemoteFailure(response.message(), response.code()))
            }
        }
    }
}