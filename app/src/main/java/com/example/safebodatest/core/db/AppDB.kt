package com.example.safebodatest.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.safebodatest.core.db.dao.UserDao
import com.example.safebodatest.core.db.tables.User

@Database(entities = [User::class],
    version = AppDB.DB_VERSION,
    autoMigrations = [],
)
abstract class AppDB : RoomDatabase() {

    companion object {
        const val DB_VERSION = 1
    }

    abstract fun userDao(): UserDao


}