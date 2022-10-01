package com.example.safebodatest.features.users_list.presentation.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.safebodatest.R
import com.example.safebodatest.databinding.ActivityUsersListBinding
import com.example.safebodatest.features.users_list.presentation.adapter.UsersListAdapter
import com.example.safebodatest.features.users_list.presentation.view_model.IUsersListViewModel
import com.example.safebodatest.features.users_list.presentation.view_model.UsersListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class UsersListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsersListBinding

    @Inject
    lateinit var viewModel: IUsersListViewModel

    @Inject
    lateinit var adapter: UsersListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_users_list)
        setViews()
        setObservers()
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.getUsersList()
        }
    }

    private fun setViews() {
        binding.usersList.adapter = adapter
    }

    private fun setObservers() {
        (viewModel as UsersListViewModel).usersListObserver.observe(this) { usersList ->
            adapter.setList(usersList)
            checkEmptyScreen(usersList.size)
        }
    }

    private fun checkEmptyScreen(listSize: Int) {
        if (listSize == 0) {
            binding.usersList.visibility = View.GONE
        } else {
            binding.usersList.visibility = View.VISIBLE
        }
    }
}