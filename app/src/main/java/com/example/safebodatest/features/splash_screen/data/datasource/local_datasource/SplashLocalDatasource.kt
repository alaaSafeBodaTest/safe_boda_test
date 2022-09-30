package com.example.safebodatest.features.splash_screen.data.datasource.local_datasource

import arrow.core.Either
import com.example.safebodatest.core.consts.Keys
import com.example.safebodatest.core.db.AppDB
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.CacheFailure
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.preferences.PreferenceManager
import java.lang.Exception
import javax.inject.Inject

class SplashLocalDatasource @Inject constructor(
    private val manager: PreferenceManager,
    private val db: AppDB
) :
    ISplashLocalDatasource {

    override fun getToken(): Either<IFailure, String> {
        val token = manager.getString(Keys.TOKEN)
        return if (token.isNullOrBlank())
            Either.Left(CacheFailure("No Token"))
        else
            Either.Right(token)
    }

    override suspend fun getUser(): Either<IFailure, User> {
        val id =
            manager.getInt(Keys.CURRENT_USER_ID) ?: return Either.Left(CacheFailure("No Id Stored"))
        val result = db.userDao().loadAllByIds(intArrayOf(id))
        return if (result.isEmpty())
            Either.Left(CacheFailure("Not Found"))
        else
            Either.Right(result.first())
    }

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