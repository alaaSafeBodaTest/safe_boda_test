package com.example.safebodatest.core.di.modules

import com.example.safebodatest.features.login.data.datasource.local_datasources.ILocalDatasource
import com.example.safebodatest.features.login.data.datasource.remote_datasources.IRemoteDatasource
import com.example.safebodatest.features.login.data.datasource.remote_datasources.RemoteDatasourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DatasourceModule {

    @Binds
    abstract fun getLoginLocalDatasource(
        datasource: ILocalDatasource
    ): ILocalDatasource

    @Binds
    abstract fun getLoginRemoteDatasource(
        datasource: RemoteDatasourceImpl
    ): IRemoteDatasource

}