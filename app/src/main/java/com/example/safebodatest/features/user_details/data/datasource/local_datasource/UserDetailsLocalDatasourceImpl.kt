package com.example.safebodatest.features.user_details.data.datasource.local_datasource

import arrow.core.Either
import com.example.safebodatest.core.db.AppDB
import com.example.safebodatest.core.db.tables.FollowRelation
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.CacheFailure
import com.example.safebodatest.core.failures.IFailure
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class UserDetailsLocalDatasourceImpl @Inject constructor(private val db: AppDB) :
    IUserDetailsLocalDatasource {

    override suspend fun loadUserByUsername(username: String): Either<IFailure, User> {
        return try {
            val user = db.userDao().loadAllByUsernames(listOf(username)).firstOrNull()
            if (user != null)
                Either.Right(user)
            else
                Either.Left(CacheFailure("Not Found"))
        } catch (e: Exception) {
            Either.Left(CacheFailure(e.message ?: "Unknown Error"))
        }
    }

    override suspend fun loadUserFollowers(username: String): Either<IFailure, List<User>> {
        return try {
            val result = db.followingRelationDao().loadAllByIds(username).firstOrNull()
            val fResult = mutableListOf<User>()
            result?.let { relation ->
                val list: List<Long> =
                    Gson().fromJson(relation.followerIds, object : TypeToken<List<Long>>() {}.type)
                val followers = db.userDao().loadAllByIds(list.map { it.toInt() }.toIntArray())
                fResult.addAll(followers)
            }
            Either.Right(fResult)
        } catch (e: java.lang.Exception) {
            Either.Left(CacheFailure(e.localizedMessage ?: ""))
        }
    }

    override suspend fun loadUserFollowings(username: String): Either<IFailure, List<User>> {
        return try {
            val result = db.followingRelationDao().loadAllByIds(username).firstOrNull()
            val fResult = mutableListOf<User>()
            result?.let { relation ->
                val list: List<Long> =
                    Gson().fromJson(relation.followingIds, object : TypeToken<List<Long>>() {}.type)
                val followers = db.userDao().loadAllByIds(list.map { it.toInt() }.toIntArray())
                fResult.addAll(followers)
            }
            Either.Right(fResult)
        } catch (e: java.lang.Exception) {
            Either.Left(CacheFailure(e.localizedMessage ?: ""))
        }
    }

    override suspend fun storeUser(user: User): Either<IFailure, Long> {
        return try {
            val result = db.userDao().insertAll(user)
            val id = result.firstOrNull()
            if (id != null)
                Either.Right(result.first())
            else
                Either.Left(CacheFailure(""))
        } catch (e: java.lang.Exception) {
            Either.Left(CacheFailure(e.localizedMessage ?: ""))
        }
    }

    override suspend fun storeUsersListLocally(list: List<User>): Either<IFailure, List<Long>> {
        return try {
            val ids = db.userDao().insertAll(*list.toTypedArray())
            if (ids.isEmpty())
                Either.Left(CacheFailure(""))
            else
                Either.Right(ids.map { it })
        } catch (e: Exception) {
            Either.Left(CacheFailure(e.message ?: ""))
        }
    }

    override suspend fun storeUserFollowers(
        username: String,
        followers: List<User>
    ): Either<IFailure, List<User>> {
        var option = db.followingRelationDao().loadAllByIds(username).firstOrNull()
        val toStore = Gson().toJson(followers.map { it.id })
        if (option == null) {
            option = FollowRelation(
                username, followerIds = toStore, followingIds = Gson().toJson(listOf<Int>())
            )
        }else{
            option.followerIds = toStore
        }
        db.followingRelationDao().insertAll(option)
        return Either.Right(followers)
    }

    override suspend fun storeUserFollowings(
        username: String,
        followings: List<User>
    ): Either<IFailure, List<User>> {
        var option = db.followingRelationDao().loadAllByIds(username).firstOrNull()
        val toStore = Gson().toJson(followings.map { it.id })
        if (option == null) {
            option = FollowRelation(
                username, followingIds = toStore, followerIds = Gson().toJson(listOf<Int>())
            )
        }else{
            option.followerIds = toStore
        }
        db.followingRelationDao().insertAll(option)
        return Either.Right(followings)
    }
}