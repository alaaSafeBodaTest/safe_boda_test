package com.example.safebodatest.features.splash_screen.domain.usecase

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.usecase_templates.IUseCaseTemplate
import com.example.safebodatest.features.splash_screen.domain.repository.ISplashRepository
import javax.inject.Inject

class FetchUserUC @Inject constructor(private val repository: ISplashRepository): IUseCaseTemplate<Boolean, User, IFailure> {

    override suspend fun runAsync(params: Boolean): Either<IFailure?, User> {
        return repository.getUser(remotely = params)
    }
}