package com.example.safebodatest.core.di.modules

import com.example.safebodatest.features.login.presentation.view_models.ILoginViewModel
import com.example.safebodatest.features.login.presentation.view_models.LoginViewModel
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

}