package com.example.safebodatest.features.user_details.presentation.viewModel

interface IUserDetailsViewModel {

    suspend fun loadUserByUsername(username: String)

}