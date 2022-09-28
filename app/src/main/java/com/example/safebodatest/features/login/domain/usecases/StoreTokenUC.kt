package com.example.safebodatest.features.login.domain.usecases

import arrow.core.Either
import com.example.safebodatest.core.api.ServiceGenerator
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.usecase_templates.IUseCaseTemplate
import javax.inject.Inject

class StoreTokenUC @Inject constructor() : IUseCaseTemplate<String, Any, IFailure> {

    override fun run(params: String): Either<IFailure?, Any> {
        super.run(params)
        ServiceGenerator.token = params
        return Either.Right(Any())
    }
}