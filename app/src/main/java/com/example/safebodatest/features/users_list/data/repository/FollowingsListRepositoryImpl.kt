package com.example.safebodatest.features.users_list.data.repository

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.features.users_list.data.datasource.remote_datasource.IFollowingsListRemoteDatasource
import com.example.safebodatest.features.users_list.data.model.adapters.FollowingEntityModelAdapter
import com.example.safebodatest.features.users_list.domain.entity.FollowingListItemEntity
import com.example.safebodatest.features.users_list.domain.repository.IFollowingsListRepository
import javax.inject.Inject

class FollowingsListRepositoryImpl @Inject constructor(private val remoteDatasource: IFollowingsListRemoteDatasource) :
    IFollowingsListRepository {

    override suspend fun getFollowingsList(page: Int): Either<IFailure, List<FollowingListItemEntity>> {
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
}