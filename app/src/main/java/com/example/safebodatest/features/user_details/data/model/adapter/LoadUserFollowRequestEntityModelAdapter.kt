package com.example.safebodatest.features.user_details.data.model.adapter

import com.example.safebodatest.core.model_templates.IDataModel
import com.example.safebodatest.features.user_details.data.model.LoadUserFollowRequestModel
import com.example.safebodatest.features.user_details.domain.entity.LoadUserFollowRequestEntity

class LoadUserFollowRequestEntityModelAdapter: IDataModel<LoadUserFollowRequestModel, LoadUserFollowRequestEntity>() {
    override fun toEntity(model: LoadUserFollowRequestModel): LoadUserFollowRequestEntity = model

    override fun toModel(entity: LoadUserFollowRequestEntity): LoadUserFollowRequestModel {
        return LoadUserFollowRequestModel(page = entity.page, username = entity.username)
    }
}