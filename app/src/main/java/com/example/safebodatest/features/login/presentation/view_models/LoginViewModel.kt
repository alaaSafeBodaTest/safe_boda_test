package com.example.safebodatest.features.login.presentation.view_models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.usecase_templates.IUseCaseTemplate
import com.example.safebodatest.features.login.domain.usecases.FetchUserUC
import com.example.safebodatest.features.login.domain.usecases.StoreTokenUC
import com.example.safebodatest.features.login.domain.usecases.StoreUserDetailsUC
import com.example.safebodatest.features.login.domain.usecases.StoreUserIdUC
import com.example.safebodatest.features.login.presentation.actions.LoginActions
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val fetchUserUC: FetchUserUC,
    private val storeTokenUC: StoreTokenUC,
    private val storeUserDetailsUC: StoreUserDetailsUC,
    private val storeUserIdUC: StoreUserIdUC,
) : ViewModel(),
    ILoginViewModel {

    val actionObserver: MutableLiveData<LoginActions> = MutableLiveData()

    val storeTokenObserver: MutableLiveData<Boolean> = MutableLiveData()

    val fetchedUserObserver: MutableLiveData<Either<IFailure?, User>> = MutableLiveData()

    override fun onSignInClicked() {
        actionObserver.postValue(LoginActions.SIGN_IN_WITH_GITHUB)
    }

    override fun storeToken(token: String) {
        super.storeToken(token)
        storeTokenUC.run(token)
        storeTokenObserver.postValue(true)
    }

    override suspend fun fetchUserAccount() {
        fetchedUserObserver.postValue(fetchUserUC.runAsync(IUseCaseTemplate.NoParams()))
    }

    override suspend fun storeUserId(id: Int) {
        storeUserIdUC.run(id)
    }

    override suspend fun storeUserDetails(user: User) {
        storeUserDetailsUC.runAsync(user)
    }
}