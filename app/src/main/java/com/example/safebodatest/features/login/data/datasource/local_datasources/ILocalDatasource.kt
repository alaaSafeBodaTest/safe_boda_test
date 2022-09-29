package com.example.safebodatest.features.login.data.datasource.local_datasources

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure

interface ILocalDatasource {

    suspend fun saveUser(user: User): Either<IFailure, Long>

}
