package com.example.safebodatest.features.user_details.data.repository

import android.content.Context
import android.widget.Toast
import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.network_utils.NetworkUtils
import com.example.safebodatest.features.user_details.data.datasource.local_datasource.IUserDetailsLocalDatasource
import com.example.safebodatest.features.user_details.data.datasource.remote_datasource.IUserDetailsRemoteDatasource
import com.example.safebodatest.features.user_details.data.model.adapter.LoadUserFollowRequestEntityModelAdapter
import com.example.safebodatest.features.user_details.domain.entity.LoadUserFollowRequestEntity
import com.example.safebodatest.features.user_details.domain.repository.IUserDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserDetailsRepositoryImpl @Inject constructor(
    private val context: Context,
    private val localDatasource: IUserDetailsLocalDatasource,
    private val remoteDatasource: IUserDetailsRemoteDatasource
) : IUserDetailsRepository {
    override suspend fun loadUserByUsernameUC(username: String): Either<IFailure, User> {
        val isOnline = NetworkUtils.hasInternetConnection(context)
        return if (isOnline) {
            loadUserOnOnlineMode(username)
        } else {
            loadUserOnOfflineMode(username)
        }
    }

    private suspend fun loadUserOnOnlineMode(username: String): Either<IFailure, User> {
        val result = remoteDatasource.loadUserByUsername(username)
        result.orNull()?.let { user ->
            localDatasource.storeUser(user)
        }
        return result
    }

    private suspend fun loadUserOnOfflineMode(username: String): Either<IFailure, User> {
        withContext(Dispatchers.Main) {
            Toast.makeText(context, "You are offline", Toast.LENGTH_LONG).show()
        }
        return localDatasource.loadUserByUsername(username)
    }

    override suspend fun loadUserFollowingsByUsername(params: LoadUserFollowRequestEntity): Either<IFailure, List<User>> {
        val model = LoadUserFollowRequestEntityModelAdapter().toModel(params)
        val result = remoteDatasource.loadUserFollowingsByUsername(model)
        return result
    }

    override suspend fun loadUserFollowersByUsername(params: LoadUserFollowRequestEntity): Either<IFailure, List<User>> {
        val model = LoadUserFollowRequestEntityModelAdapter().toModel(params)
        val result = remoteDatasource.loadUserFollowersByUsername(model)
        return result
    }
}