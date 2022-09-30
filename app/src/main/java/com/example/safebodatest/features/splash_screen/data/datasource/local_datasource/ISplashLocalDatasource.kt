package com.example.safebodatest.features.splash_screen.data.datasource.local_datasource

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure

interface ISplashLocalDatasource {

    fun getToken(): Either<IFailure, String>

    suspend fun getUser(): Either<IFailure, User>

    suspend fun saveUser(user: User): Either<IFailure, Long>

}