package com.example.safebodatest.features.user_details.presentation.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.safebodatest.R
import com.example.safebodatest.core.consts.Keys
import com.example.safebodatest.databinding.FragmentUserFollowingBinding
import com.example.safebodatest.features.user_details.presentation.adapters.UserFollowListAdapter
import com.example.safebodatest.features.user_details.presentation.viewModel.IUserFollowListViewModel
import com.example.safebodatest.features.user_details.presentation.viewModel.UserFollowListViewModelImpl
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class UserFollowingFragment(val username: String? = null) : Fragment() {

    lateinit var binding: FragmentUserFollowingBinding

    @Inject
    lateinit var adapter: UserFollowListAdapter

    @Inject
    lateinit var viewModel: IUserFollowListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_user_following, container, false)

        username?.let { safeUsername ->
            getUserFollowings(safeUsername)
        }
        setViews()
        setObservers()
        return binding.root
    }

    private fun setObservers() {
        (viewModel as UserFollowListViewModelImpl).getUserFollowingsObserver.observe(viewLifecycleOwner){ either ->
            either.fold(ifLeft = {
                Snackbar.make(binding.root, "${it?.message}", Snackbar.LENGTH_LONG).show()
            }, ifRight = {
                adapter.setList(it)
            })
        }
    }

    private fun getUserFollowings(safeUsername: String) {
        lifecycleScope.launch(Dispatchers.IO){
            viewModel.getUserFollowings(safeUsername)
        }
    }

    private fun setViews() {
        adapter.listener = {
            it.login?.let { username -> goToUserDetails(username) }
        }
        adapter.isFollowing = true
        binding.itemsList.adapter = adapter
    }

    private fun goToUserDetails(username: String) {
        val intent = Intent(requireActivity(), UserDetailsActivity::class.java)
        intent.putExtra(Keys.USERNAME, username)
        startActivity(intent)
    }

}