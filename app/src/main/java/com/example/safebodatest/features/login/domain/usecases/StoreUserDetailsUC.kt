package com.example.safebodatest.features.login.domain.usecases

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.usecase_templates.IUseCaseTemplate
import com.example.safebodatest.core.user_utils.UserManager
import com.example.safebodatest.features.login.domain.repositories.ILoginRepository
import javax.inject.Inject

class StoreUserDetailsUC @Inject constructor(private val repo: ILoginRepository) : IUseCaseTemplate<User, Long, IFailure> {

    override suspend fun runAsync(params: User): Either<IFailure?, Long> {
        UserManager.setUser(params)
        return repo.storeUserDetails(user = params)
    }
}