package com.example.safebodatest.features.login.domain.usecases

import arrow.core.Either
import com.example.safebodatest.core.api.ServiceGenerator
import com.example.safebodatest.core.consts.Keys
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.preferences.PreferenceManager
import com.example.safebodatest.core.usecase_templates.IUseCaseTemplate
import javax.inject.Inject

class StoreTokenUC @Inject constructor() : IUseCaseTemplate<String, Any, IFailure> {

    @Inject
    lateinit var sharedPreferences: PreferenceManager

    override fun run(params: String): Either<IFailure?, Any> {
        super.run(params)
        ServiceGenerator.token = params
        sharedPreferences.putString(Keys.TOKEN, params)
        return Either.Right(Any())
    }
}