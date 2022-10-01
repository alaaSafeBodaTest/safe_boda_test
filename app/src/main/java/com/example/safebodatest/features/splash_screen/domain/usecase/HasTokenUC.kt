package com.example.safebodatest.features.splash_screen.domain.usecase

import arrow.core.Either
import com.example.safebodatest.core.api.ServiceGenerator
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.usecase_templates.IUseCaseTemplate
import com.example.safebodatest.features.splash_screen.domain.repository.ISplashRepository
import javax.inject.Inject

class HasTokenUC @Inject constructor(val repository: ISplashRepository) : IUseCaseTemplate<IUseCaseTemplate.NoParams, String, IFailure> {

    override fun run(params: IUseCaseTemplate.NoParams): Either<IFailure?, String> {
        val result = repository.getToken()
        if(result.isRight()) {
                ServiceGenerator.token = result.orNull() ?: ""
            }
        return result
    }
}