package com.example.safebodatest.features.login.data.repositories

import arrow.core.Either
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.features.login.data.datasource.remote_datasources.IRemoteDatasource
import com.example.safebodatest.features.login.domain.repositories.ILoginRepository
import javax.inject.Inject

class LoginRepoImpl @Inject constructor(private val remoteDatasource: IRemoteDatasource) : ILoginRepository {

    override suspend fun fetchUser(): Either<IFailure, Boolean> {
        return remoteDatasource.fetchUser()
    }
}