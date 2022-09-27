package com.example.safebodatest.core.di.modules

import android.content.Context
import androidx.room.Room
import com.example.safebodatest.core.db.AppDB
import com.example.safebodatest.core.db.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun getDB(
        @ApplicationContext appContext: Context
    ): AppDB = Room.databaseBuilder(
        appContext,
        AppDB::class.java,
        "safeboda-db",
    ).build()

    @Provides
    @Singleton
    fun getUserDao(db: AppDB): UserDao = db.userDao()

}