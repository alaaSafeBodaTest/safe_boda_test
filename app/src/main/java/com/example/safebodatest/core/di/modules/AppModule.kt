package com.example.safebodatest.core.di.modules

import android.content.Context
import androidx.room.Room
import com.example.safebodatest.core.db.AppDB
import com.example.safebodatest.core.db.dao.FollowingDao
import com.example.safebodatest.core.db.dao.UserDao
import com.example.safebodatest.core.preferences.PreferenceManager
import com.example.safebodatest.features.users_list.data.datasource.local_datasource.IFollowingsListLocalDatasource
import com.example.safebodatest.features.users_list.data.datasource.remote_datasource.IFollowingsListRemoteDatasource
import com.example.safebodatest.features.users_list.data.repository.FollowingsListRepositoryImpl
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
    fun getFollowingsListRepository(
        @ApplicationContext appContext: Context,
        localDatasource: IFollowingsListLocalDatasource,
        remoteDatasource: IFollowingsListRemoteDatasource
    ) = FollowingsListRepositoryImpl(context = appContext, localDatasource = localDatasource, remoteDatasource = remoteDatasource)

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

    @Provides
    @Singleton
    fun getFollowingDao(db: AppDB): FollowingDao = db.followingDao()

    @Provides
    @Singleton
    fun getSharedPreference(
        @ApplicationContext
        context: Context
    ): PreferenceManager =
        PreferenceManager(context = context)

}