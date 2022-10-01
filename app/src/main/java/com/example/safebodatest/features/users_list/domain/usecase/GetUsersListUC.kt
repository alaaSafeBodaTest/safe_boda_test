package com.example.safebodatest.features.users_list.domain.usecase

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.usecase_templates.IUseCaseTemplate
import com.example.safebodatest.features.users_list.domain.repository.IUsersListRepository
import javax.inject.Inject

class GetUsersListUC @Inject constructor(val repository: IUsersListRepository) : IUseCaseTemplate<IUseCaseTemplate.NoParams, List<User>, IFailure> {

    override suspend fun runAsync(params: IUseCaseTemplate.NoParams): Either<IFailure?, List<User>> {
        return repository.getUsersList()
    }
}