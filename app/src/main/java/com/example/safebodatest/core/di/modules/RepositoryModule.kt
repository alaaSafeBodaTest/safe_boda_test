package com.example.safebodatest.core.di.modules

import com.example.safebodatest.features.login.data.repositories.LoginRepoImpl
import com.example.safebodatest.features.login.domain.repositories.ILoginRepository
import com.example.safebodatest.features.search_user.data.repository.SearchUserRepositoryImpl
import com.example.safebodatest.features.search_user.domain.repository.ISearchUserRepository
import com.example.safebodatest.features.splash_screen.data.repository.SplashRepositoryImpl
import com.example.safebodatest.features.splash_screen.domain.repository.ISplashRepository
import com.example.safebodatest.features.user_details.data.repository.UserDetailsRepositoryImpl
import com.example.safebodatest.features.user_details.domain.repository.IUserDetailsRepository
import com.example.safebodatest.features.users_list.data.repository.FollowingsListRepositoryImpl
import com.example.safebodatest.features.users_list.domain.repository.IFollowingsListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun getSearchRepository(
        repoImpl: SearchUserRepositoryImpl
    ): ISearchUserRepository

    @Binds
    abstract fun getUserDetailsRepository(
        repoImpl: UserDetailsRepositoryImpl
    ): IUserDetailsRepository

    @Binds
    abstract fun getFollowingsListRepository(
        repoImpl: FollowingsListRepositoryImpl
    ): IFollowingsListRepository

    @Binds
    abstract fun getLoginRepository(
        repoImpl: LoginRepoImpl
    ): ILoginRepository

    @Binds
    abstract fun getSplashLoginRepository(
        repoImpl: SplashRepositoryImpl
    ): ISplashRepository

}