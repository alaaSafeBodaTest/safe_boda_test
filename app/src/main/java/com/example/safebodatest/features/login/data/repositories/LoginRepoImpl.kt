package com.example.safebodatest.features.login.data.repositories

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.features.login.data.datasource.local_datasources.ILocalDatasource
import com.example.safebodatest.features.login.data.datasource.remote_datasources.IRemoteDatasource
import com.example.safebodatest.features.login.domain.repositories.ILoginRepository
import javax.inject.Inject

class LoginRepoImpl @Inject constructor(
    private val localDatasource: ILocalDatasource,
    private val remoteDatasource: IRemoteDatasource
) : ILoginRepository {

    override suspend fun fetchUser(): Either<IFailure, User> {
        return remoteDatasource.fetchUser()
    }

    override suspend fun storeUserDetails(user: User): Either<IFailure, Long> {
        return localDatasource.saveUser(user)
    }
}