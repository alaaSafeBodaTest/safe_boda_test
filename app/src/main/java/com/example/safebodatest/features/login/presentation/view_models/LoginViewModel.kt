package com.example.safebodatest.features.login.presentation.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.safebodatest.features.login.presentation.actions.LoginActions
import javax.inject.Inject

class LoginViewModel @Inject constructor()
    : ViewModel(),
    ILoginViewModel {

    val actionObserver: MutableLiveData<LoginActions> = MutableLiveData()

    override fun onSignInClicked() {
        actionObserver.postValue(LoginActions.SIGN_IN_WITH_GITHUB)
    }
}