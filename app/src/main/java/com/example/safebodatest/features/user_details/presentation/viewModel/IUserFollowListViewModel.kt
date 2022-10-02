package com.example.safebodatest.features.user_details.presentation.viewModel

interface IUserFollowListViewModel {

    suspend fun getUserFollowers(username: String) {}

    suspend fun getUserFollowings(username: String) {}

}