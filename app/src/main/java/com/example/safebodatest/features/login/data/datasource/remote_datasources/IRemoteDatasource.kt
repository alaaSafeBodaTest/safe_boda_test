package com.example.safebodatest.features.login.data.datasource.remote_datasources

import arrow.core.Either
import com.example.safebodatest.core.failures.IFailure

interface IRemoteDatasource {

    suspend fun fetchUser(): Either<IFailure, Boolean>

}