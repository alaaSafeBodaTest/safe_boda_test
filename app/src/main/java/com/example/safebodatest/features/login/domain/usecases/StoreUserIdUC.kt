package com.example.safebodatest.features.login.domain.usecases

import arrow.core.Either
import com.example.safebodatest.core.consts.Keys
import com.example.safebodatest.core.failures.CacheFailure
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.preferences.PreferenceManager
import com.example.safebodatest.core.usecase_templates.IUseCaseTemplate
import javax.inject.Inject

class StoreUserIdUC @Inject constructor(private val sharedPreferences: PreferenceManager) : IUseCaseTemplate<Int, Any, IFailure> {

    override fun run(params: Int): Either<IFailure?, Any> {
        super.run(params)
        sharedPreferences.putInt(Keys.CURRENT_USER_ID, params)
        val id = sharedPreferences.getInt(Keys.CURRENT_USER_ID)
        return if(id == -1){
            Either.Left(CacheFailure("ID Not Saved"))
        }else
            Either.Right(Any())
    }
}