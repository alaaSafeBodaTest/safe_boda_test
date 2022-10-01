package com.example.safebodatest.features.users_list.data.model.adapters

import com.example.safebodatest.core.model_templates.IDataModel
import com.example.safebodatest.features.users_list.data.model.FollowingListItemModel
import com.example.safebodatest.features.users_list.domain.entity.FollowingListItemEntity

class FollowingEntityModelAdapter: IDataModel<FollowingListItemModel, FollowingListItemEntity>() {
    override fun toEntity(model: FollowingListItemModel): FollowingListItemEntity = model

    override fun toModel(entity: FollowingListItemEntity): FollowingListItemModel {
        return FollowingListItemModel(id = entity.id, avatarURL = entity.avatar_url, userName = entity.login)
    }
}