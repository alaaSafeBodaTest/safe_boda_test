package com.example.safebodatest.features.login.data.datasource.local_datasources

import arrow.core.Either
import com.example.safebodatest.core.db.AppDB
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.CacheFailure
import com.example.safebodatest.core.failures.IFailure
import java.lang.Exception
import javax.inject.Inject

class DBLocalDatasource : ILocalDatasource {
    @Inject
    lateinit var db: AppDB

    override suspend fun saveUser(user: User): Either<IFailure, Boolean> {
        return try{
            val result = db.userDao().insertAll(user)
            Either.Right(true)
        }catch (e: Exception){
            Either.Left(CacheFailure(e.localizedMessage ?:""))
        }
    }
}