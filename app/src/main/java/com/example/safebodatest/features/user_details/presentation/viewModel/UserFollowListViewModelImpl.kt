package com.example.safebodatest.features.user_details.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.features.user_details.domain.usecase.LoadUserFollowers
import com.example.safebodatest.features.user_details.domain.usecase.LoadUserFollowings
import javax.inject.Inject

class UserFollowListViewModelImpl @Inject constructor(
    private val loadUserFollowings: LoadUserFollowings,
    private val loadUserFollowers: LoadUserFollowers,
): IUserFollowListViewModel {

    val getUserFollowingsObserver = MutableLiveData<Either<IFailure?, List<User>>>()

    val getUserFollowersObserver = MutableLiveData<Either<IFailure?, List<User>>>()

    override suspend fun getUserFollowers(username: String) {
        getUserFollowersObserver.postValue(loadUserFollowers.runAsync(username))
    }

    override suspend fun getUserFollowings(username: String) {
        getUserFollowingsObserver.postValue(loadUserFollowings.runAsync(username))
    }
}