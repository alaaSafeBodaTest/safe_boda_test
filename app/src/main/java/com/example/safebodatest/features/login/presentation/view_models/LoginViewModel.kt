package com.example.safebodatest.features.login.presentation.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.safebodatest.core.usecase_templates.IUseCaseTemplate
import com.example.safebodatest.features.login.domain.usecases.FetchUserUC
import com.example.safebodatest.features.login.domain.usecases.StoreTokenUC
import com.example.safebodatest.features.login.presentation.actions.LoginActions
import kotlinx.coroutines.*
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val fetchUserUC: FetchUserUC,
    private val storeTokenUC: StoreTokenUC
) : ViewModel(),
    ILoginViewModel {

    val actionObserver: MutableLiveData<LoginActions> = MutableLiveData()

    val storeTokenObserver: MutableLiveData<Boolean> = MutableLiveData()

    val fetchedUserObserver: MutableLiveData<Boolean> = MutableLiveData()

    override fun onSignInClicked() {
        actionObserver.postValue(LoginActions.SIGN_IN_WITH_GITHUB)
    }

    override fun storeToken(token: String) {
        super.storeToken(token)
        storeTokenUC.run(token)
        storeTokenObserver.postValue(true)
    }

    override suspend fun fetchUserAccount() {
        withContext(Dispatchers.IO) {
            fetchUserUC.runAsync(IUseCaseTemplate.NoParams())
        }
    }
}