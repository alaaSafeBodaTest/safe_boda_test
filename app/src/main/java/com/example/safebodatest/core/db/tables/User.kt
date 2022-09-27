package com.example.safebodatest.core.db.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @PrimaryKey val username: String,
)