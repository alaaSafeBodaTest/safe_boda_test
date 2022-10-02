package com.example.safebodatest.core.di.modules

import com.example.safebodatest.features.login.data.datasource.local_datasources.ILoginLocalDatasource
import com.example.safebodatest.features.login.data.datasource.local_datasources.LoginDBLocalDatasource
import com.example.safebodatest.features.login.data.datasource.remote_datasources.ILoginRemoteDatasource
import com.example.safebodatest.features.login.data.datasource.remote_datasources.LoginRemoteDatasourceImpl
import com.example.safebodatest.features.splash_screen.data.datasource.local_datasource.ISplashLocalDatasource
import com.example.safebodatest.features.splash_screen.data.datasource.local_datasource.SplashLocalDatasource
import com.example.safebodatest.features.splash_screen.data.datasource.remote_datasource.ISplashRemoteDatasource
import com.example.safebodatest.features.splash_screen.data.datasource.remote_datasource.SplashRemoteDatasourceImpl
import com.example.safebodatest.features.user_details.data.datasource.local_datasource.IUserDetailsLocalDatasource
import com.example.safebodatest.features.user_details.data.datasource.local_datasource.UserDetailsLocalDatasourceImpl
import com.example.safebodatest.features.user_details.data.datasource.remote_datasource.IUserDetailsRemoteDatasource
import com.example.safebodatest.features.user_details.data.datasource.remote_datasource.UserDetailsRemoteDatasourceImpl
import com.example.safebodatest.features.users_list.data.datasource.local_datasource.FollowingsListLocalDatasourceImpl
import com.example.safebodatest.features.users_list.data.datasource.local_datasource.IFollowingsListLocalDatasource
import com.example.safebodatest.features.users_list.data.datasource.remote_datasource.FollowingsListRemoteDatasourceImpl
import com.example.safebodatest.features.users_list.data.datasource.remote_datasource.IFollowingsListRemoteDatasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DatasourceModule {

    @Binds
    abstract fun getUserDetailsRemoteDatasource(
        datasource: UserDetailsRemoteDatasourceImpl
    ): IUserDetailsRemoteDatasource

    @Binds
    abstract fun getUserDetailsLocalDatasource(
        datasource: UserDetailsLocalDatasourceImpl
    ): IUserDetailsLocalDatasource

    @Binds
    abstract fun getUsersListLocalDatasource(
        datasource: FollowingsListLocalDatasourceImpl
    ): IFollowingsListLocalDatasource

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