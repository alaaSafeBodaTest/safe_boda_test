package com.example.safebodatest.features.users_list.presentation.view_model

import arrow.core.Either
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.features.users_list.presentation.data_holder.FollowingListItem

interface IFollowingsListViewModel {

    suspend fun getFollowingsList()

    suspend fun storeFollowingsList(list: List<FollowingListItem>)

    fun logout(): Either<IFailure?, Any>

}