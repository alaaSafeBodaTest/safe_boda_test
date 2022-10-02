package com.example.safebodatest.features.user_details.domain.repository

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure

interface IUserDetailsRepository {

    suspend fun loadUserByUsernameUC(username: String): Either<IFailure, User>

}