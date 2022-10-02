package com.example.safebodatest.features.user_details.domain.usecase

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.usecase_templates.IUseCaseTemplate
import com.example.safebodatest.features.user_details.domain.repository.IUserDetailsRepository
import javax.inject.Inject

class LoadUserByUsernameUC @Inject constructor(private val repository: IUserDetailsRepository)
    : IUseCaseTemplate<String, User, IFailure> {

    override suspend fun runAsync(params: String): Either<IFailure?, User> {
        return repository.loadUserByUsernameUC(params)
    }
}