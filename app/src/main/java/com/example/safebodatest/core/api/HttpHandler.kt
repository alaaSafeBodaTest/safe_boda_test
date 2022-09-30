package com.example.safebodatest.core.api

import arrow.core.Either
import com.example.safebodatest.core.failures.RemoteFailure
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

interface HttpHandler {

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