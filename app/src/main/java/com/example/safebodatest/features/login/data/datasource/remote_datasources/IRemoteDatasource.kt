package com.example.safebodatest.features.login.data.datasource.remote_datasources

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.failures.RemoteFailure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

interface IRemoteDatasource {

    suspend fun fetchUser(): Either<IFailure, User>

    suspend fun <T> handleRequest(t: suspend () -> Either<RemoteFailure, T>): Either<RemoteFailure, T> {
        return try {
            t.invoke()
        } catch (io: IOException) {
            Either.Left(RemoteFailure(io.message ?: "Unknown Message", 0))
        } catch (httpE: HttpException) {
            Either.Left(RemoteFailure(httpE.message ?: "Unknown Message", httpE.code()))
        } catch (e: Exception) {
            Either.Left(RemoteFailure(e.message ?: "Unknown Message", 0))
        }
    }

}