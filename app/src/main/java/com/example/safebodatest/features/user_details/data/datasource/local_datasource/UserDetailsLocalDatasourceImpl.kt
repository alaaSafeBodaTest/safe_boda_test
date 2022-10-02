package com.example.safebodatest.features.user_details.data.datasource.local_datasource

import arrow.core.Either
import com.example.safebodatest.core.db.AppDB
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.CacheFailure
import com.example.safebodatest.core.failures.IFailure
import javax.inject.Inject

class UserDetailsLocalDatasourceImpl @Inject constructor(private val db: AppDB): IUserDetailsLocalDatasource {

    override suspend fun loadUserByUsername(username: String): Either<IFailure, User> {
        return try {
            val user = db.userDao().loadAllByUsernames(listOf(username)).firstOrNull()
            if(user != null)
                Either.Right(user)
            else
                Either.Left(CacheFailure("Not Found"))
        }catch (e: Exception){
            Either.Left(CacheFailure(e.message ?: "Unknown Error"))
        }
    }

    override suspend fun storeUser(user: User): Either<IFailure, Long> {
        return try{
            val result = db.userDao().insertAll(user)
            val id = result.firstOrNull()
            if(id != null)
                Either.Right(result.first())
            else
                Either.Left(CacheFailure(""))
        }catch (e: java.lang.Exception){
            Either.Left(CacheFailure(e.localizedMessage ?:""))
        }
    }
}