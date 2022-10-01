package com.example.safebodatest.core.di.modules

import com.example.safebodatest.features.login.data.repositories.LoginRepoImpl
import com.example.safebodatest.features.login.domain.repositories.ILoginRepository
import com.example.safebodatest.features.splash_screen.data.repository.SplashRepositoryImpl
import com.example.safebodatest.features.splash_screen.domain.repository.ISplashRepository
import com.example.safebodatest.features.users_list.data.repository.UsersListRepositoryImpl
import com.example.safebodatest.features.users_list.domain.repository.IUsersListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun getUsersListRepository(
        repoImpl: UsersListRepositoryImpl
    ): IUsersListRepository

    @Binds
    abstract fun getLoginRepository(
        repoImpl: LoginRepoImpl
    ): ILoginRepository

    @Binds
    abstract fun getSplashLoginRepository(
        repoImpl: SplashRepositoryImpl
    ): ISplashRepository

}