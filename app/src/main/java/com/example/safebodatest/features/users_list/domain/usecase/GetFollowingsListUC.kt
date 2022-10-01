package com.example.safebodatest.features.users_list.domain.usecase

import arrow.core.Either
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.usecase_templates.IUseCaseTemplate
import com.example.safebodatest.features.users_list.data.model.adapters.FollowingEntityModelAdapter
import com.example.safebodatest.features.users_list.domain.entity.FollowingListItemEntity
import com.example.safebodatest.features.users_list.domain.repository.IFollowingsListRepository
import com.example.safebodatest.features.users_list.presentation.data_holder.FollowingListItem
import com.example.safebodatest.features.users_list.presentation.data_holder.adapter.FollowingListItemDataHolderEntityAdapter
import javax.inject.Inject

class GetFollowingsListUC @Inject constructor(val repository: IFollowingsListRepository) :
    IUseCaseTemplate<Int, List<FollowingListItem>, IFailure> {

    override suspend fun runAsync(params: Int): Either<IFailure?, List<FollowingListItem>> {
        val result = repository.getFollowingsList(params)
        return result.fold<Either<IFailure?, List<FollowingListItem>>>(ifLeft = {
            return Either.Left(it)
        }, ifRight = { list ->
            val resultList = mutableListOf<FollowingListItem>()
            list.forEach { model ->
                resultList.add(FollowingListItemDataHolderEntityAdapter().toModel(model))
            }
            return Either.Right(resultList)
        })
    }
}