package com.example.safebodatest.features.splash_screen.data.repository

import android.content.Context
import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.network_utils.NetworkUtils
import com.example.safebodatest.features.splash_screen.data.datasource.local_datasource.ISplashLocalDatasource
import com.example.safebodatest.features.splash_screen.data.datasource.remote_datasource.ISplashRemoteDatasource
import com.example.safebodatest.features.splash_screen.domain.repository.ISplashRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SplashRepositoryImpl @Inject constructor(
    private val localDatasource: ISplashLocalDatasource,
    private val remoteDatasource: ISplashRemoteDatasource,
) :
    ISplashRepository {

    @ApplicationContext
    lateinit var context: Context

    override fun getToken(): Either<IFailure?, String> {
        return localDatasource.getToken()
    }

    override suspend fun getUser(remotely: Boolean): Either<IFailure?, User> {
        return if (remotely) {
            remoteDatasource.getUser()
        } else {
            localDatasource.getUser()
        }
    }

    override suspend fun saveUser(user: User): Either<IFailure?, Long> {
        return localDatasource.saveUser(user)
    }
}