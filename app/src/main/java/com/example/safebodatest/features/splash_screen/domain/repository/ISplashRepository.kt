package com.example.safebodatest.features.splash_screen.domain.repository

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure

interface ISplashRepository {

    fun getToken(): Either<IFailure?, String>

    suspend fun saveUser(user: User): Either<IFailure?, Long>

    suspend fun getUser(remotely: Boolean): Either<IFailure?, User>
}