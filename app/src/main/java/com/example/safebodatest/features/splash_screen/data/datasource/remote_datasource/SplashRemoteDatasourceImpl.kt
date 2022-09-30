package com.example.safebodatest.features.splash_screen.data.datasource.remote_datasource

import arrow.core.Either
import com.example.safebodatest.core.api.ServiceGenerator
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.failures.RemoteFailure
import javax.inject.Inject

class SplashRemoteDatasourceImpl @Inject constructor() : ISplashRemoteDatasource {
    override suspend fun getUser(): Either<IFailure, User> {
        return handleRequest {
            val response = ServiceGenerator.api.getUser()
            return@handleRequest if (response.isSuccessful) {
                val user = response.body()
                if (user != null)
                    Either.Right(user)
                else
                    Either.Left(RemoteFailure("", 200))
            } else {
                Either.Left(RemoteFailure(response.message(), response.code()))
            }
        }
    }
}