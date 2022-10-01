package com.example.safebodatest.features.users_list.presentation.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import arrow.core.Either
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.usecase_templates.IUseCaseTemplate
import com.example.safebodatest.features.users_list.domain.entity.FollowingListItemEntity
import com.example.safebodatest.features.users_list.domain.usecase.GetFollowingsListUC
import com.example.safebodatest.features.users_list.domain.usecase.StoreFollowingsListUC
import com.example.safebodatest.features.users_list.presentation.data_holder.FollowingListItem
import javax.inject.Inject

class FollowingsListViewModel @Inject constructor(
    private val getFollowingsList: GetFollowingsListUC,
    private val storeFollowingsListUC: StoreFollowingsListUC,
) : ViewModel(), IFollowingsListViewModel {

    val followingsListObserver = MutableLiveData<Either<IFailure?, List<FollowingListItem>>>()
    val storeFollowingsListObserver = MutableLiveData<Either<IFailure?, List<Long>>>()

    var page = 0

    var lastPageLoaded = false

    override suspend fun getFollowingsList() {
        followingsListObserver.postValue(getFollowingsList.runAsync(page + 1))
    }

    override suspend fun storeFollowingsList(list: List<FollowingListItem>) {
        storeFollowingsListObserver.postValue(storeFollowingsListUC.runAsync(list))
    }


}