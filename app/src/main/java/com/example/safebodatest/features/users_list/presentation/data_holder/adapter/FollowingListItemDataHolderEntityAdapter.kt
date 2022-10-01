package com.example.safebodatest.features.users_list.presentation.data_holder.adapter

import com.example.safebodatest.core.model_templates.IDataModel
import com.example.safebodatest.features.users_list.domain.entity.FollowingListItemEntity
import com.example.safebodatest.features.users_list.presentation.data_holder.FollowingListItem

class FollowingListItemDataHolderEntityAdapter: IDataModel<FollowingListItem, FollowingListItemEntity>() {
    override fun toEntity(model: FollowingListItem): FollowingListItemEntity {
        return FollowingListItemEntity(id = model.id, avatar_url = model.imgUrl, login = model.username)
    }

    override fun toModel(entity: FollowingListItemEntity): FollowingListItem {
        return FollowingListItem(id = entity.id, imgUrl = entity.avatar_url, username = entity.login)
    }
}