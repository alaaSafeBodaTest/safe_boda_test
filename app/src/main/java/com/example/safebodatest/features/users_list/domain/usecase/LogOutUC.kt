package com.example.safebodatest.features.users_list.domain.usecase

import arrow.core.Either
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.usecase_templates.IUseCaseTemplate
import com.example.safebodatest.features.users_list.domain.repository.IFollowingsListRepository
import javax.inject.Inject

class LogOutUC @Inject constructor(private val repository: IFollowingsListRepository): IUseCaseTemplate<IUseCaseTemplate.NoParams, Any, IFailure> {

    override fun run(params: IUseCaseTemplate.NoParams): Either<IFailure?, Any> {
        return repository.logout()
    }
}