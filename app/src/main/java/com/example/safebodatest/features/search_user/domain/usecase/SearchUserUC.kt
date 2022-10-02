package com.example.safebodatest.features.search_user.domain.usecase

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.usecase_templates.IUseCaseTemplate
import com.example.safebodatest.features.search_user.domain.repository.ISearchUserRepository
import javax.inject.Inject

class SearchUserUC @Inject constructor(
    private val repository: ISearchUserRepository,
) : IUseCaseTemplate<String, User, IFailure> {

    override suspend fun runAsync(params: String): Either<IFailure?, User> {
        return repository.searchUser(params)
    }
}