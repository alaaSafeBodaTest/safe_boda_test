package com.example.safebodatest.features.users_list.data.repository

import arrow.core.Either
import com.example.safebodatest.core.api.ServiceGenerator
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.failures.RemoteFailure
import com.example.safebodatest.features.users_list.data.datasource.remote_datasource.IUsersListRemoteDatasource
import com.example.safebodatest.features.users_list.domain.repository.IUsersListRepository
import javax.inject.Inject

class UsersListRepositoryImpl @Inject constructor(private val remoteDatasource: IUsersListRemoteDatasource) : IUsersListRepository {

    override suspend fun getUsersList(): Either<IFailure, List<User>> {
        return remoteDatasource.getUsersList()
    }
}