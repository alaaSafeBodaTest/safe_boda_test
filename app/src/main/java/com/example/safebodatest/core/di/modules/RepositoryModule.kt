package com.example.safebodatest.core.di.modules

import com.example.safebodatest.features.login.data.repositories.LoginRepoImpl
import com.example.safebodatest.features.login.domain.repositories.ILoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun getLoginRepository(
        repoImpl: LoginRepoImpl
    ): ILoginRepository

}