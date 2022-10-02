package com.example.safebodatest.features.search_user.presentation.view_model

interface ISearchUserViewModel {

    suspend fun searchForUser(username: String)

}