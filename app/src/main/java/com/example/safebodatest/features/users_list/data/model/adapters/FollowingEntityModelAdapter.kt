package com.example.safebodatest.features.users_list.data.model.adapters

import com.example.safebodatest.core.model_templates.IDataModel
import com.example.safebodatest.features.users_list.data.model.FollowingListItemModel
import com.example.safebodatest.features.users_list.domain.entity.FollowingListItemEntity

class FollowingEntityModelAdapter : IDataModel<FollowingListItemModel, FollowingListItemEntity>() {
    override fun toEntity(model: FollowingListItemModel): FollowingListItemEntity = model

    override fun toModel(entity: FollowingListItemEntity): FollowingListItemModel {
        return FollowingListItemModel(
            id = entity.id,
            avatar_url = entity.avatar_url,
            login = entity.login,
            events_url = entity.events_url,
            followers_url = entity.followers_url,
            following_url = entity.following_url,
            gists_url = entity.gists_url,
            gravatar_id = entity.gravatar_id,
            html_url = entity.html_url,
            node_id = entity.node_id,
            organizations_url = entity.organizations_url,
            received_events_url = entity.received_events_url,
            repos_url = entity.repos_url,
            site_admin = entity.site_admin,
            starred_url = entity.starred_url,
            subscriptions_url = entity.subscriptions_url,
            type = entity.type,
            url = entity.url
        )
    }
}