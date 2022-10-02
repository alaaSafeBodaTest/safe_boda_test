package com.example.safebodatest.core.di.modules

import com.example.safebodatest.features.user_details.presentation.adapters.UserFollowListAdapter
import com.example.safebodatest.features.users_list.presentation.adapter.FollowingsListAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Provides
    fun getFollowingsAdapter() = FollowingsListAdapter()

    @Provides
    fun getUsersAdapter() = UserFollowListAdapter()

}