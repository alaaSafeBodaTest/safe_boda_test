package com.example.safebodatest.features.login.domain.repositories

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure

interface ILoginRepository {

    suspend fun fetchUser(): Either<IFailure, User>

    suspend fun storeUserDetails(user: User): Either<IFailure, Long>

}