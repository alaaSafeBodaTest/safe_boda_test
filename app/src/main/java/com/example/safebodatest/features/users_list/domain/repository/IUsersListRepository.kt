package com.example.safebodatest.features.users_list.domain.repository

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure

interface IUsersListRepository {

    suspend fun getUsersList() : Either<IFailure, List<User>>

}