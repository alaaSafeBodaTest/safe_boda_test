package com.example.safebodatest.features.users_list.domain.usecase

import arrow.core.Either
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.usecase_templates.IUseCaseTemplate
import com.example.safebodatest.features.users_list.data.model.adapters.FollowingEntityModelAdapter
import com.example.safebodatest.features.users_list.domain.repository.IFollowingsListRepository
import com.example.safebodatest.features.users_list.presentation.data_holder.FollowingListItem
import com.example.safebodatest.features.users_list.presentation.data_holder.adapter.FollowingListItemDataHolderEntityAdapter
import javax.inject.Inject

class StoreFollowingsListUC @Inject constructor(private val repository: IFollowingsListRepository)
    : IUseCaseTemplate<List<FollowingListItem>, List<Long>, IFailure> {

    override suspend fun runAsync(params: List<FollowingListItem>): Either<IFailure?, List<Long>> {
        val adapter = FollowingListItemDataHolderEntityAdapter()
        val list = params.map { adapter.toEntity(it) }
        return repository.storeFollowingsList(list)
    }
}