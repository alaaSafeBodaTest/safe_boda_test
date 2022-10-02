package com.example.safebodatest.features.users_list.data.model

import com.example.safebodatest.features.users_list.domain.entity.FollowingListItemEntity

class FollowingListItemModel(
    id: Int,
    avatar_url: String? = null,
    events_url: String? = null,
    followers_url: String? = null,
    following_url: String? = null,
    gists_url: String? = null,
    gravatar_id: String? = null,
    html_url: String? = null,
    login: String? = null,
    node_id: String? = null,
    organizations_url: String? = null,
    received_events_url: String? = null,
    repos_url: String? = null,
    site_admin: Boolean? = null,
    starred_url: String? = null,
    subscriptions_url: String? = null,
    type: String? = null,
    url: String? = null
) : FollowingListItemEntity(id = id, avatar_url = avatar_url, login = login,) {
}