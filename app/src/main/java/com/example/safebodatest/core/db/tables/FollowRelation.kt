package com.example.safebodatest.core.db.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "follow_relation")
data class FollowRelation(
    @PrimaryKey val username: String,
    var followingIds: String,
    var followerIds: String,
)