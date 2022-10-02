package com.example.safebodatest.features.users_list.data.datasource.local_datasource

import arrow.core.Either
import com.example.safebodatest.core.db.AppDB
import com.example.safebodatest.core.failures.CacheFailure
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.preferences.PreferenceManager
import com.example.safebodatest.features.users_list.data.model.FollowingListItemModel
import com.example.safebodatest.features.users_list.data.model.adapters.FollowingModelTableAdapter
import com.example.safebodatest.features.users_list.data.model.adapters.FollowingUserAdapter
import javax.inject.Inject

class FollowingsListLocalDatasourceImpl @Inject constructor(val db: AppDB, val preferenceManager: PreferenceManager) :
    IFollowingsListLocalDatasource {

    override suspend fun storeFollowingsListLocally(list: List<FollowingListItemModel>): Either<IFailure, List<Long>> {
        val adapter = FollowingModelTableAdapter()
        val rows = list.map { adapter.toEntity(it) }
        val followingUserAdapter = FollowingUserAdapter()
        val usersRows = rows.map { followingUserAdapter.toModel(it) }.toMutableList()
        return try {
            val ids = db.followingDao().insertAll(*rows.toTypedArray()).map { it.toInt() }
            usersRows.removeAll { ids.contains(it.id) }
            db.userDao().insertAll(*usersRows.toTypedArray())
            if(ids.isEmpty())
                Either.Left(CacheFailure(""))
            else
                Either.Right(ids.map { it.toLong() })
        }catch (e: Exception){
            Either.Left(CacheFailure(e.message ?: ""))
        }
    }

    override fun logout(): Either<IFailure, Any> {
        return try {
            preferenceManager.clearAll()
            db.clearAllTables()
            Either.Right(Any())
        }catch (e: Exception){
            Either.Left(CacheFailure(e.message ?: ""))
        }
    }

    override suspend fun getFollowingsList(page: Int): Either<IFailure, List<FollowingListItemModel>> {
        val adapter = FollowingModelTableAdapter()
        val rows = db.followingDao().getAll()
        return try {
            val result = rows.map { adapter.toModel(it) }
            return Either.Right(result)
        }catch (e: Exception){
            Either.Left(CacheFailure(e.message ?: ""))
        }
    }
}