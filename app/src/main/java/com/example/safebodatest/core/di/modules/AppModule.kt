package com.example.safebodatest.core.di.modules

import android.content.Context
import androidx.room.Room
import com.example.safebodatest.core.db.AppDB
import com.example.safebodatest.core.db.dao.FollowRelationDao
import com.example.safebodatest.core.db.dao.FollowingDao
import com.example.safebodatest.core.db.dao.UserDao
import com.example.safebodatest.core.preferences.PreferenceManager
import com.example.safebodatest.features.search_user.data.datasource.local_datasource.SearchUserLocalDatasourceImpl
import com.example.safebodatest.features.search_user.data.datasource.remote_datasource.SearchUserRemoteDatasourceImpl
import com.example.safebodatest.features.search_user.data.repository.SearchUserRepositoryImpl
import com.example.safebodatest.features.user_details.data.datasource.local_datasource.UserDetailsLocalDatasourceImpl
import com.example.safebodatest.features.user_details.data.datasource.remote_datasource.UserDetailsRemoteDatasourceImpl
import com.example.safebodatest.features.user_details.data.repository.UserDetailsRepositoryImpl
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
    fun getSearchUserRepository(
        @ApplicationContext appContext: Context,
        localDatasource: SearchUserLocalDatasourceImpl,
        remoteDatasource: SearchUserRemoteDatasourceImpl
    ) = SearchUserRepositoryImpl(context = appContext, localDatasource = localDatasource, remoteDatasource = remoteDatasource)

    @Provides
    fun getUserDetailsRepository(
        @ApplicationContext appContext: Context,
        localDatasource: UserDetailsLocalDatasourceImpl,
        remoteDatasource: UserDetailsRemoteDatasourceImpl
    ) = UserDetailsRepositoryImpl(context = appContext, localDatasource = localDatasource, remoteDatasource = remoteDatasource)

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
    fun getFollowingRelationDao(db: AppDB): FollowRelationDao = db.followingRelationDao()

    @Provides
    @Singleton
    fun getSharedPreference(
        @ApplicationContext
        context: Context
    ): PreferenceManager =
        PreferenceManager(context = context)

}