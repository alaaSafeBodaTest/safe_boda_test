package com.example.safebodatest.features.login.domain.usecases

import arrow.core.Either
import com.example.safebodatest.core.consts.Keys
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.preferences.PreferenceManager
import com.example.safebodatest.core.usecase_templates.IUseCaseTemplate
import javax.inject.Inject

class StoreUserIdUC @Inject constructor(val sharedPreferences: PreferenceManager) : IUseCaseTemplate<Int, Any, IFailure> {

    override fun run(params: Int): Either<IFailure?, Any> {
        super.run(params)
        sharedPreferences.putInt(Keys.CURRENT_USER_ID, params)
        return Either.Right(Any())
    }
}