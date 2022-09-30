package com.example.safebodatest.features.login.data.datasource.remote_datasources

import arrow.core.Either
import com.example.safebodatest.core.api.HttpHandler
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.failures.RemoteFailure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

interface ILoginRemoteDatasource : HttpHandler{

    suspend fun fetchUser(): Either<IFailure, User>

}