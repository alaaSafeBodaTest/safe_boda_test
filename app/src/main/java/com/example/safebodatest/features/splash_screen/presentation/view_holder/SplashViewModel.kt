package com.example.safebodatest.features.splash_screen.presentation.view_holder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.usecase_templates.IUseCaseTemplate
import com.example.safebodatest.features.splash_screen.domain.usecase.FetchUserUC
import com.example.safebodatest.features.splash_screen.domain.usecase.HasTokenUC
import com.example.safebodatest.features.splash_screen.domain.usecase.StoreUserUC
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val tokenUC: HasTokenUC,
    private val fetchUserUC: FetchUserUC,
    private val storeUserUC: StoreUserUC,
) : ViewModel(), ISplashViewModel {

    val getTokenObserver = MutableLiveData<Either<IFailure?, String>>()

    val getUserObserver = MutableLiveData<Either<IFailure?, User>>()

    val storeUser = MutableLiveData<Either<IFailure?, Any>>()

    override fun hasToken() {
        getTokenObserver.postValue(tokenUC.run(IUseCaseTemplate.NoParams()))
    }

    override suspend fun fetchUser(remotely: Boolean) {
        getUserObserver.postValue(fetchUserUC.runAsync(remotely))
    }

    override suspend fun storeUser(safeUser: User) {
        storeUser.postValue(storeUserUC.runAsync(safeUser))
    }
}