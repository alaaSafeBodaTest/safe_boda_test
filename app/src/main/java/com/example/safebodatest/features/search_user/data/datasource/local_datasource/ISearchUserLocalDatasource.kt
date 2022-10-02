package com.example.safebodatest.features.search_user.data.datasource.local_datasource

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure

interface ISearchUserLocalDatasource {

    suspend fun searchUser(username: String): Either<IFailure, User>

}