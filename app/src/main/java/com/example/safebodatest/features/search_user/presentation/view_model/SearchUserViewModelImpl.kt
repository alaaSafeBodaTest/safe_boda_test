package com.example.safebodatest.features.search_user.presentation.view_model

import androidx.lifecycle.MutableLiveData
import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.features.search_user.domain.usecase.SearchUserUC
import javax.inject.Inject

class SearchUserViewModelImpl @Inject constructor(
    private val searchUserUC: SearchUserUC,
): ISearchUserViewModel {

    val searchObserver = MutableLiveData<Either<IFailure?, User>>()

    override suspend fun searchForUser(username: String){
        searchObserver.postValue(searchUserUC.runAsync(username))
    }
}