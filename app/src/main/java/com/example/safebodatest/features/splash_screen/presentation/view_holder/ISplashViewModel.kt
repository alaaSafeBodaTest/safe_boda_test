package com.example.safebodatest.features.splash_screen.presentation.view_holder

import com.example.safebodatest.core.db.tables.User

interface ISplashViewModel {

    fun hasToken()

    suspend fun fetchUser(remotely: Boolean)

    suspend fun storeUser(safeUser: User)

}