package com.example.safebodatest.features.users_list.data.repository

import android.content.Context
import android.widget.Toast
import arrow.core.Either
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.network_utils.NetworkUtils
import com.example.safebodatest.features.users_list.data.datasource.local_datasource.IFollowingsListLocalDatasource
import com.example.safebodatest.features.users_list.data.datasource.remote_datasource.IFollowingsListRemoteDatasource
import com.example.safebodatest.features.users_list.data.model.adapters.FollowingEntityModelAdapter
import com.example.safebodatest.features.users_list.domain.entity.FollowingListItemEntity
import com.example.safebodatest.features.users_list.domain.repository.IFollowingsListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FollowingsListRepositoryImpl @Inject constructor(
    private val context: Context,
    private val remoteDatasource: IFollowingsListRemoteDatasource,
    private val localDatasource: IFollowingsListLocalDatasource,
) :
    IFollowingsListRepository {

    override suspend fun getFollowingsList(page: Int): Either<IFailure, List<FollowingListItemEntity>> {
        val isOnline = NetworkUtils.hasInternetConnection(context)
        return if (isOnline)
            fetchOnlineUserFollowings(page = page)
        else {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "You are offline", Toast.LENGTH_SHORT).show()
            }
            fetchOfflineUserFollowings(page)

        }
    }

    private suspend fun fetchOnlineUserFollowings(page: Int): Either<IFailure, List<FollowingListItemEntity>> {
        val result = remoteDatasource.getFollowingsList(page)
        if (result.isRight()) {
            result.orNull().let { list ->
                val resultList = mutableListOf<FollowingListItemEntity>()
                list?.forEach { model ->
                    resultList.add(FollowingEntityModelAdapter().toEntity(model))
                }
                return Either.Right(resultList)
            }
        } else {
            return result
        }
    }

    private suspend fun fetchOfflineUserFollowings(page: Int): Either<IFailure, List<FollowingListItemEntity>> {
        val result = localDatasource.getFollowingsList(page)
        if (result.isRight()) {
            result.orNull().let { list ->
                val resultList = mutableListOf<FollowingListItemEntity>()
                list?.forEach { model ->
                    resultList.add(FollowingEntityModelAdapter().toEntity(model))
                }
                return Either.Right(resultList)
            }
        } else {
            return result
        }
    }

    override suspend fun storeFollowingsList(list: List<FollowingListItemEntity>): Either<IFailure, List<Long>> {
        val adapter = FollowingEntityModelAdapter()
        val modelList = list.map { adapter.toModel(it) }
        return localDatasource.storeFollowingsListLocally(modelList)
    }

    override fun logout(): Either<IFailure, Any> {
        return localDatasource.logout()
    }
}