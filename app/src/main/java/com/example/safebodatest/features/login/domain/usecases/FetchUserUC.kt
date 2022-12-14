package com.example.safebodatest.features.login.domain.usecases

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.usecase_templates.IUseCaseTemplate
import com.example.safebodatest.features.login.domain.repositories.ILoginRepository
import javax.inject.Inject

class FetchUserUC @Inject constructor(private val repository: ILoginRepository)
    : IUseCaseTemplate<IUseCaseTemplate.NoParams, User, IFailure> {

    override suspend fun runAsync(params: IUseCaseTemplate.NoParams): Either<IFailure?, User> {
        return repository.fetchUser()
    }
}