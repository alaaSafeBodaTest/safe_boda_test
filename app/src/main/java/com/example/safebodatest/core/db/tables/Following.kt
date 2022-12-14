package com.example.safebodatest.core.db.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Following")
data class Following(
    @PrimaryKey
    val id: Int,
    val avatar_url: String? = null,
    val events_url: String? = null,
    val followers_url: String? = null,
    val following_url: String? = null,
    val gists_url: String? = null,
    val gravatar_id: String? = null,
    val html_url: String? = null,
    val login: String? = null,
    val node_id: String? = null,
    val organizations_url: String? = null,
    val received_events_url: String? = null,
    val repos_url: String? = null,
    val site_admin: Boolean? = null,
    val starred_url: String? = null,
    val subscriptions_url: String? = null,
    val type: String? = null,
    val url: String? = null
)