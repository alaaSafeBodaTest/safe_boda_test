package com.example.safebodatest.features.login.data.datasource.local_datasources

import arrow.core.Either
import com.example.safebodatest.core.db.AppDB
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.CacheFailure
import com.example.safebodatest.core.failures.IFailure
import java.lang.Exception
import javax.inject.Inject

class LoginDBLocalDatasource @Inject constructor(val db: AppDB) : ILoginLocalDatasource {

    override suspend fun saveUser(user: User): Either<IFailure, Long> {
        return try{
            val result = db.userDao().insertAll(user)
            println(db.userDao().getAll())
            val id = result.firstOrNull()
            if(id != null)
                Either.Right(result.first())
            else
                Either.Left(CacheFailure(""))
        }catch (e: Exception){
            Either.Left(CacheFailure(e.localizedMessage ?:""))
        }
    }
}