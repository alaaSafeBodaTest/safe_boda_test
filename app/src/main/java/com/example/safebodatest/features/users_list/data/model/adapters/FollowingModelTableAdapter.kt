package com.example.safebodatest.features.users_list.data.model.adapters

import com.example.safebodatest.core.db.tables.Following
import com.example.safebodatest.core.model_templates.IDataModel
import com.example.safebodatest.features.users_list.data.model.FollowingListItemModel

class FollowingModelTableAdapter: IDataModel<FollowingListItemModel, Following>() {
    override fun toEntity(model: FollowingListItemModel): Following {
        return Following(
            id = model.id,
            avatar_url = model.avatar_url,
            login = model.login,
            events_url = model.events_url,
            followers_url = model.followers_url,
            following_url = model.following_url,
            gists_url = model.gists_url,
            gravatar_id = model.gravatar_id,
            html_url = model.html_url,
            node_id = model.node_id,
            organizations_url = model.organizations_url,
            received_events_url = model.received_events_url,
            repos_url = model.repos_url,
            site_admin = model.site_admin,
            starred_url = model.starred_url,
            subscriptions_url = model.subscriptions_url,
            type = model.type,
            url = model.url
        )
    }

    override fun toModel(entity: Following): FollowingListItemModel {
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