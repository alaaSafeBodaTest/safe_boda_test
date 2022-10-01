package com.example.safebodatest.features.users_list.presentation.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.safebodatest.R
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.databinding.ActivityUsersListBinding
import com.example.safebodatest.features.login.presentation.view.SignInActivity
import com.example.safebodatest.features.users_list.presentation.adapter.FollowingsListAdapter
import com.example.safebodatest.features.users_list.presentation.data_holder.FollowingListItem
import com.example.safebodatest.features.users_list.presentation.view_model.FollowingsListViewModel
import com.example.safebodatest.features.users_list.presentation.view_model.IFollowingsListViewModel
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
            binding.nestedScrollView.setOnScrollChangeListener { v: NestedScrollView, _, scrollY, _, _ ->
                if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                    fetchFollowers()
                }
            }
        }
    }

    private fun setObservers() {
        (viewModel as FollowingsListViewModel).followingsListObserver.observe(this) { result ->
            result.fold(ifLeft = {
                onFailedRetrievingFollowings(it)
            }, ifRight = { usersList ->
                onListUpdated(usersList)
            })
        }
        (viewModel as FollowingsListViewModel).storeFollowingsListObserver.observe(this) { either ->
            either.fold(ifLeft = {
                onFailedRetrievingFollowings(it)
            },
                ifRight = {
                    println(it)
                })
        }
    }

    private fun onFailedRetrievingFollowings(it: IFailure?) {
        Snackbar.make(
            binding.root,
            "Failed to get followers because of: ${it?.message}",
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun onListUpdated(usersList: List<FollowingListItem>) {
        adapter.addAll(usersList)
        if (usersList.isNotEmpty()) {
            (viewModel as FollowingsListViewModel).page++
            if (usersList.isNotEmpty())
                lifecycleScope.launch(Dispatchers.IO) {
                    viewModel.storeFollowingsList(usersList)
                }
        } else
            (viewModel as FollowingsListViewModel).lastPageLoaded = true
        checkEmptyScreen(adapter.itemCount)
    }

    private fun checkEmptyScreen(listSize: Int) {
        if (listSize == 0) {
            binding.usersList.visibility = View.GONE
        } else {
            binding.usersList.visibility = View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.followers_action_bar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.profile_homepage_menu_item -> {
                Log.e(javaClass.simpleName, "onOptionsItemSelected: Profile Clicked")
            }
            R.id.logout_homepage_menu_item -> {
                lifecycleScope.launch(Dispatchers.IO) {
                    onLogoutClicked()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onLogoutClicked() {
        val result = viewModel.logout()
        result.fold(ifLeft = {
            Snackbar.make(
                binding.root,
                "We couldn't log out ${it?.message}",
                Snackbar.LENGTH_LONG
            ).show()
        }, ifRight = {
            val intent = Intent(this@FollowingsListActivity, SignInActivity::class.java)
            startActivity(intent)
            finish()
        })
    }
}