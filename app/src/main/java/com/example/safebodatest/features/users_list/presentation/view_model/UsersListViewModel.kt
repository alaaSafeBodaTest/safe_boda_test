package com.example.safebodatest.features.users_list.presentation.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.safebodatest.core.usecase_templates.IUseCaseTemplate
import com.example.safebodatest.features.users_list.domain.usecase.GetUsersListUC
import com.example.safebodatest.features.users_list.presentation.data_holder.UserListItem
import javax.inject.Inject

class UsersListViewModel @Inject constructor(
    val getUsersList: GetUsersListUC
) : ViewModel(), IUsersListViewModel{

    val usersListObserver = MutableLiveData<List<UserListItem>>(listOf())

    override suspend fun getUsersList() {
        usersListObserver.postValue(listOf(
            UserListItem(id = 1, name = "Alaa", username = "AlaaKira", imgUrl = "https://avatars.githubusercontent.com/u/49841907?v=4"),
            UserListItem(id = 2, name = "Alaa2", username = "AlaaKira2", imgUrl = null),
        ))
        println(getUsersList.runAsync(IUseCaseTemplate.NoParams()))
    }


}