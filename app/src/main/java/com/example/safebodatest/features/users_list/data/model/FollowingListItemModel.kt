package com.example.safebodatest.features.users_list.data.model

import com.example.safebodatest.features.users_list.domain.entity.FollowingListItemEntity

class FollowingListItemModel(id: Int, avatarURL: String?, userName: String?)
    : FollowingListItemEntity(id = id, avatar_url = avatarURL, login = userName) {
}