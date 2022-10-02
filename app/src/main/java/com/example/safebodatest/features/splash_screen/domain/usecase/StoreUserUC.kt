package com.example.safebodatest.features.splash_screen.domain.usecase

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.usecase_templates.IUseCaseTemplate
import com.example.safebodatest.core.user_utils.UserManager
import com.example.safebodatest.features.splash_screen.domain.repository.ISplashRepository
import javax.inject.Inject

class StoreUserUC @Inject constructor(private val repository: ISplashRepository): IUseCaseTemplate<User, Long, IFailure> {

    override suspend fun runAsync(params: User): Either<IFailure?, Long> {
        UserManager.setUser(params)
        return repository.saveUser(params)
    }
}