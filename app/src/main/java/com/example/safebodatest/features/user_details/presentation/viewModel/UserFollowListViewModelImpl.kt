package com.example.safebodatest.features.user_details.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.features.user_details.domain.entity.LoadUserFollowRequestEntity
import com.example.safebodatest.features.user_details.domain.usecase.LoadUserFollowers
import com.example.safebodatest.features.user_details.domain.usecase.LoadUserFollowings
import javax.inject.Inject

class UserFollowListViewModelImpl @Inject constructor(
    private val loadUserFollowings: LoadUserFollowings,
    private val loadUserFollowers: LoadUserFollowers,
): IUserFollowListViewModel {

    val getUserFollowingsObserver = MutableLiveData<Either<IFailure?, List<User>>>()

    val getUserFollowersObserver = MutableLiveData<Either<IFailure?, List<User>>>()

    var followersPage = 0
    var followingsPage = 0
    var lastFollowingPageLoaded = false
    var lastFollowerPageLoaded = false

    override suspend fun getUserFollowers(username: String) {
        val result = loadUserFollowers.runAsync(LoadUserFollowRequestEntity(page = followersPage +1, username = username))
        result.orNull()?.let {
            followersPage++
            if(it.isEmpty())
                lastFollowerPageLoaded = true
        }
        getUserFollowersObserver.postValue(result)
    }

    override suspend fun getUserFollowings(username: String) {
        val result = loadUserFollowings.runAsync(LoadUserFollowRequestEntity(page = followingsPage +1, username = username))
        result.orNull()?.let {
            followingsPage++
            if(it.isEmpty())
                lastFollowingPageLoaded = true
        }
        getUserFollowingsObserver.postValue(result)
    }
}