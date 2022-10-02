package com.example.safebodatest.core.di.modules

import com.example.safebodatest.features.login.presentation.view_models.ILoginViewModel
import com.example.safebodatest.features.login.presentation.view_models.LoginViewModel
import com.example.safebodatest.features.splash_screen.presentation.view_holder.ISplashViewModel
import com.example.safebodatest.features.splash_screen.presentation.view_holder.SplashViewModel
import com.example.safebodatest.features.user_details.presentation.viewModel.IUserDetailsViewModel
import com.example.safebodatest.features.user_details.presentation.viewModel.IUserFollowListViewModel
import com.example.safebodatest.features.user_details.presentation.viewModel.UserDetailsViewModelImpl
import com.example.safebodatest.features.user_details.presentation.viewModel.UserFollowListViewModelImpl
import com.example.safebodatest.features.users_list.presentation.view_model.FollowingsListViewModel
import com.example.safebodatest.features.users_list.presentation.view_model.IFollowingsListViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ViewModelModules {

    @Binds
    abstract fun getUserFollowViewModel(
        viewModel: UserFollowListViewModelImpl
    ): IUserFollowListViewModel

    @Binds
    abstract fun getUserDetailsViewModel(
        viewModel: UserDetailsViewModelImpl
    ): IUserDetailsViewModel

    @Binds
    abstract fun getUsersViewModel(
        viewModel: FollowingsListViewModel
    ): IFollowingsListViewModel

    @Binds
    abstract fun getLoginViewModel(
        viewModel: LoginViewModel
    ): ILoginViewModel

    @Binds
    abstract fun getSplashViewModel(
        viewModel: SplashViewModel
    ): ISplashViewModel

}