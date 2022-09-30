package com.example.safebodatest.features.users_list.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.safebodatest.R
import com.example.safebodatest.databinding.ActivityUsersListBinding
import com.example.safebodatest.features.users_list.presentation.adapter.UsersListAdapter
import com.example.safebodatest.features.users_list.presentation.data_holder.UserListItem
import com.example.safebodatest.features.users_list.presentation.view_model.IUsersListViewModel
import com.example.safebodatest.features.users_list.presentation.view_model.UsersListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UsersListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsersListBinding

    @Inject
    lateinit var viewModel: IUsersListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_users_list)
        setViews()
        setObservers()
    }

    private fun setViews() {
        val adapter = UsersListAdapter()
        adapter.setList(listOf(
            UserListItem(id = 1, name = "Alaa", username = "AlaaKira", imgUrl = null),
            UserListItem(id = 2, name = "Alaa2", username = "AlaaKira2", imgUrl = null),
        ))
        binding.usersList.adapter = adapter
    }

    private fun setObservers() {
        (viewModel as UsersListViewModel).usersListObserver.observe(this) {
        }
    }

    private fun checkEmptyScreen(listSize: Int){
        if(listSize == 0){
            binding.usersList.visibility = View.GONE
        }else{
            binding.usersList.visibility = View.VISIBLE
        }
    }
}