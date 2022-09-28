package com.example.safebodatest.features.login.data.datasource.remote_datasources

import android.util.Log
import arrow.core.Either
import com.example.safebodatest.core.api.ServiceGenerator
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.failures.RemoteFailure
import javax.inject.Inject

class RemoteDatasourceImpl @Inject constructor() : IRemoteDatasource {

    override suspend fun fetchUser(): Either<IFailure, Boolean> {
        val response = ServiceGenerator.api.getUser()
        return if (response.isSuccessful) {
            Either.Right(true)
        } else {
            Either.Left(RemoteFailure(response.message(), response.code()))
        }
    }
}