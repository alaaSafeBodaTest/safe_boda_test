package com.example.safebodatest.features.login.presentation.view_models

import com.example.safebodatest.core.db.tables.User

interface ILoginViewModel {

    fun onSignInClicked() {}

    fun storeToken(token: String) {}

    suspend fun fetchUserAccount() {}

    suspend fun storeUserId(id: Int) {}

    suspend fun storeUserDetails(user: User) {}

}