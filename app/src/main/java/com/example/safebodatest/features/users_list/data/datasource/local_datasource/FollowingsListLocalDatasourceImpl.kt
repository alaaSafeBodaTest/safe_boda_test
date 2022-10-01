package com.example.safebodatest.features.users_list.data.datasource.local_datasource

import arrow.core.Either
import com.example.safebodatest.core.db.AppDB
import com.example.safebodatest.core.failures.CacheFailure
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.features.users_list.data.model.FollowingListItemModel
import com.example.safebodatest.features.users_list.data.model.adapters.FollowingModelTableAdapter
import java.lang.Exception
import javax.inject.Inject

class FollowingsListLocalDatasourceImpl @Inject constructor(val db: AppDB) :
    IFollowingsListLocalDatasource {

    override suspend fun storeFollowingsListLocally(list: List<FollowingListItemModel>): Either<IFailure, List<Long>> {
        val adapter = FollowingModelTableAdapter()
        val rows = list.map { adapter.toEntity(it) }
        return try {
            val ids = db.followingDao().insertAll(*rows.toTypedArray())
            if(ids.isEmpty())
                Either.Left(CacheFailure(""))
            else
                Either.Right(ids)
        }catch (e: Exception){
            Either.Left(CacheFailure(e.message ?: ""))
        }
    }
}