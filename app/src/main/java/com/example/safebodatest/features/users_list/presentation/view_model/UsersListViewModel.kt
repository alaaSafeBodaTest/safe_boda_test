package com.example.safebodatest.features.users_list.presentation.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.safebodatest.core.db.tables.User
import javax.inject.Inject

class UsersListViewModel @Inject constructor(

) : ViewModel(), IUsersListViewModel{

    val usersListObserver = MutableLiveData<List<User>>(listOf())

    override fun getUsersList() {
        usersListObserver.postValue(listOf())
    }
}