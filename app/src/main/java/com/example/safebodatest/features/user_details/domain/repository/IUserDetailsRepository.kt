package com.example.safebodatest.features.user_details.domain.repository

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.features.user_details.domain.entity.LoadUserFollowRequestEntity

interface IUserDetailsRepository {

    suspend fun loadUserByUsernameUC(username: String): Either<IFailure, User>
    suspend fun loadUserFollowingsByUsername(params: LoadUserFollowRequestEntity): Either<IFailure, List<User>>
    suspend fun loadUserFollowersByUsername(params: LoadUserFollowRequestEntity): Either<IFailure, List<User>>

}