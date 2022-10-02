package com.example.safebodatest.features.search_user.domain.repository

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure

interface ISearchUserRepository {

    suspend fun searchUser(username: String): Either<IFailure, User>

}