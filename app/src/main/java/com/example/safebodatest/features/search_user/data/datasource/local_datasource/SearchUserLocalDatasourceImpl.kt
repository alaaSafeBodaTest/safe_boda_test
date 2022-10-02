package com.example.safebodatest.features.search_user.data.datasource.local_datasource

import arrow.core.Either
import com.example.safebodatest.core.db.AppDB
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.CacheFailure
import com.example.safebodatest.core.failures.IFailure
import javax.inject.Inject

class SearchUserLocalDatasourceImpl @Inject constructor(private val db: AppDB) :
    ISearchUserLocalDatasource {

    override suspend fun searchUser(username: String): Either<IFailure, User> {
        try {
            val result = db.userDao().loadAllByUsernames(listOf(username)).firstOrNull()
            result?.let { user ->
                return Either.Right(user)
            }
            return Either.Left(CacheFailure("Not Found"))
        } catch (e: Exception) {
            return Either.Left(CacheFailure(e.message.toString()))
        }
    }

}