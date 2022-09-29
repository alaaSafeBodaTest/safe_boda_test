package com.example.safebodatest.core.db.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @PrimaryKey
    val id: Int = 0,
    val avatar_url: String? = "",
    val bio: String? = "",
    val blog: String? = "",
    val collaborators: Int? = 0,
    val company: String? = "",
    val created_at: String? = "",
    val disk_usage: Int? = 0,
    val email: String? = "",
    val events_url: String? = "",
    val followers: Int? = 0,
    val followers_url: String? = "",
    val following: Int? = 0,
    val following_url: String? = "",
    val gists_url: String? = "",
    val gravatar_id: String? = "",
    val hireable: Boolean? = false,
    val html_url: String? = "",
    val location: String? = "",
    val login: String? = "",
    val name: String? = "",
    val node_id: String? = "",
    val organizations_url: String? = "",
    val owned_private_repos: Int? = 0,
    val private_gists: Int? = 0,
    val public_gists: Int? = 0,
    val public_repos: Int? = 0,
    val received_events_url: String? = "",
    val repos_url: String? = "",
    val site_admin: Boolean? = false,
    val starred_url: String? = "",
    val subscriptions_url: String? = "",
    val total_private_repos: Int? = 0,
    val twitter_username: String? = "",
    val two_factor_authentication: Boolean? = false,
    val type: String? = "",
    val updated_at: String? = "",
    val url: String? = ""
)