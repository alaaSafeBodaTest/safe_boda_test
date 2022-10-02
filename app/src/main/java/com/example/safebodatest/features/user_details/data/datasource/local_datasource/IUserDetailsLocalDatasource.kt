package com.example.safebodatest.features.user_details.data.datasource.local_datasource

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure

interface IUserDetailsLocalDatasource {
    suspend fun loadUserByUsername(username: String): Either<IFailure, User>
    suspend fun loadUserFollowers(username: String): Either<IFailure, List<User>>
    suspend fun storeUserFollowers(username: String, followers: List<User>): Either<IFailure, List<User>>
    suspend fun loadUserFollowings(username: String): Either<IFailure, List<User>>
    suspend fun storeUserFollowings(username: String, followings: List<User>): Either<IFailure, List<User>>
    suspend fun storeUser(user: User): Either<IFailure, Long>
    suspend fun storeUsersListLocally(list: List<User>): Either<IFailure, List<Long>>
}