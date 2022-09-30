package com.example.safebodatest.core.di.modules

import com.example.safebodatest.features.login.presentation.view_models.ILoginViewModel
import com.example.safebodatest.features.login.presentation.view_models.LoginViewModel
import com.example.safebodatest.features.splash_screen.presentation.view_holder.ISplashViewModel
import com.example.safebodatest.features.splash_screen.presentation.view_holder.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ViewModelModules {

    @Binds
    abstract fun getLoginViewModel(
        viewModel: LoginViewModel
    ): ILoginViewModel

    @Binds
    abstract fun getSplashViewModel(
        viewModel: SplashViewModel
    ): ISplashViewModel

}