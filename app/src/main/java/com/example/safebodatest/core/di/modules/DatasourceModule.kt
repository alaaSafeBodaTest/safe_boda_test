package com.example.safebodatest.core.di.modules

import com.example.safebodatest.features.login.data.datasource.local_datasources.LoginDBLocalDatasource
import com.example.safebodatest.features.login.data.datasource.local_datasources.ILoginLocalDatasource
import com.example.safebodatest.features.login.data.datasource.remote_datasources.ILoginRemoteDatasource
import com.example.safebodatest.features.login.data.datasource.remote_datasources.LoginRemoteDatasourceImpl
import com.example.safebodatest.features.splash_screen.data.datasource.local_datasource.ISplashLocalDatasource
import com.example.safebodatest.features.splash_screen.data.datasource.local_datasource.SplashLocalDatasource
import com.example.safebodatest.features.splash_screen.data.datasource.remote_datasource.ISplashRemoteDatasource
import com.example.safebodatest.features.splash_screen.data.datasource.remote_datasource.SplashRemoteDatasourceImpl
import com.example.safebodatest.features.users_list.data.datasource.remote_datasource.IFollowingsListRemoteDatasource
import com.example.safebodatest.features.users_list.data.datasource.remote_datasource.FollowingsListRemoteDatasourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DatasourceModule {

    @Binds
    abstract fun getUsersListRemoteDatasource(
        datasource: FollowingsListRemoteDatasourceImpl
    ): IFollowingsListRemoteDatasource

    @Binds
    abstract fun getLoginLocalDatasource(
        datasource: LoginDBLocalDatasource
    ): ILoginLocalDatasource

    @Binds
    abstract fun getLoginRemoteDatasource(
        datasource: LoginRemoteDatasourceImpl
    ): ILoginRemoteDatasource

    @Binds
    abstract fun getSplashRemoteDatasource(
        datasource: SplashRemoteDatasourceImpl
    ): ISplashRemoteDatasource

    @Binds
    abstract fun getSplashLocalDatasource(
        datasource: SplashLocalDatasource
    ): ISplashLocalDatasource

}