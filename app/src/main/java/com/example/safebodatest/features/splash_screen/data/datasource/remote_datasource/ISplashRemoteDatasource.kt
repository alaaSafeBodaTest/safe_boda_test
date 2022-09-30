package com.example.safebodatest.features.splash_screen.data.datasource.remote_datasource

import arrow.core.Either
import com.example.safebodatest.core.api.HttpHandler
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure

interface ISplashRemoteDatasource: HttpHandler {

    suspend fun getUser(): Either<IFailure, User>

}