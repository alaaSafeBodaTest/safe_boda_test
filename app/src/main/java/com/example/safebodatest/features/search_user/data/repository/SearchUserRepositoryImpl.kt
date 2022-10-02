package com.example.safebodatest.features.search_user.data.repository

import android.content.Context
import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.network_utils.NetworkUtils
import com.example.safebodatest.features.search_user.data.datasource.local_datasource.ISearchUserLocalDatasource
import com.example.safebodatest.features.search_user.data.datasource.remote_datasource.ISearchUserRemoteDatasource
import com.example.safebodatest.features.search_user.domain.repository.ISearchUserRepository
import javax.inject.Inject

class SearchUserRepositoryImpl @Inject constructor(
    private val context: Context,
    private val localDatasource: ISearchUserLocalDatasource,
    private val remoteDatasource: ISearchUserRemoteDatasource,
) : ISearchUserRepository {

    override suspend fun searchUser(username: String): Either<IFailure, User> {
        val isOnline = NetworkUtils.hasInternetConnection(context)
        return if(isOnline){
            searchRemotely(username)
        }else{
            searchLocally(username)
        }
    }

    private suspend fun searchLocally(username: String): Either<IFailure, User> {
        return localDatasource.searchUser(username)
    }

    private suspend fun searchRemotely(username: String): Either<IFailure, User> {
        return remoteDatasource.searchUser(username)
    }
}