package com.example.safebodatest.features.users_list.presentation.view

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.safebodatest.R
import com.example.safebodatest.databinding.ActivityUsersListBinding
import com.example.safebodatest.features.users_list.presentation.adapter.FollowingsListAdapter
import com.example.safebodatest.features.users_list.presentation.view_model.IFollowingsListViewModel
import com.example.safebodatest.features.users_list.presentation.view_model.FollowingsListViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FollowingsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsersListBinding

    @Inject
    lateinit var viewModel: IFollowingsListViewModel

    @Inject
    lateinit var adapter: FollowingsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_users_list)
        setViews()
        setObservers()
    }

    override fun onResume() {
        super.onResume()
        fetchFollowers()
    }

    private fun fetchFollowers() {
        if (!(viewModel as FollowingsListViewModel).lastPageLoaded)
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.getFollowingsList()
            }
    }

    private fun setViews() {
        binding.usersList.adapter = adapter
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.nestedScrollView.setOnScrollChangeListener { v: NestedScrollView, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                    fetchFollowers()
                }
            }
        }
    }

    private fun setObservers() {
        (viewModel as FollowingsListViewModel).followingsListObserver.observe(this) { result ->
            result.fold(ifLeft = {
                Snackbar.make(
                    binding.root,
                    "Failed to get followers because of: ${it?.message}",
                    Snackbar.LENGTH_LONG
                ).show()
            }, ifRight = { usersList ->
                adapter.addAll(usersList)
                if (adapter.itemCount > 0)
                    (viewModel as FollowingsListViewModel).page++
                else
                    (viewModel as FollowingsListViewModel).lastPageLoaded = true
                checkEmptyScreen(adapter.itemCount)
            })
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