package com.example.safebodatest.features.user_details.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.features.user_details.domain.usecase.LoadUserByUsernameUC
import javax.inject.Inject

class UserDetailsViewModelImpl @Inject constructor(
    private val loadUserByUsernameUC: LoadUserByUsernameUC,
): IUserDetailsViewModel {

    val loadUserByUsernameObserver = MutableLiveData<Either<IFailure?, User>>()

    override suspend fun loadUserByUsername(username: String) {
        loadUserByUsernameObserver.postValue(
            loadUserByUsernameUC.runAsync(username)
        )
    }
}