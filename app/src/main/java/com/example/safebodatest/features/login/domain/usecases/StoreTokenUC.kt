package com.example.safebodatest.features.login.domain.usecases

import arrow.core.Either
import com.example.safebodatest.core.api.ServiceGenerator
import com.example.safebodatest.core.consts.Keys
import com.example.safebodatest.core.failures.CacheFailure
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.preferences.PreferenceManager
import com.example.safebodatest.core.usecase_templates.IUseCaseTemplate
import javax.inject.Inject

class StoreTokenUC @Inject constructor(private val preferenceManager: PreferenceManager) : IUseCaseTemplate<String, Any, IFailure> {

    override fun run(params: String): Either<IFailure?, Any> {
        super.run(params)
        ServiceGenerator.token = params
        preferenceManager.putString(Keys.TOKEN, params)
        val token = preferenceManager.getString(Keys.TOKEN)
        return if(token.isNullOrBlank()){
            Either.Left(CacheFailure("Token Not Saved"))
        }else
            Either.Right(Any())
    }
}