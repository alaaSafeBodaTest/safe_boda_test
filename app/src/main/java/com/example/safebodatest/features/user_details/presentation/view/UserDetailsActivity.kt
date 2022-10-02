package com.example.safebodatest.features.user_details.presentation.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.safebodatest.R
import com.example.safebodatest.core.consts.Keys
import com.example.safebodatest.core.db.AppDB
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.databinding.ActivityUserDetailsBinding
import com.example.safebodatest.features.user_details.presentation.viewModel.IUserDetailsViewModel
import com.example.safebodatest.features.user_details.presentation.viewModel.UserDetailsViewModelImpl
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class UserDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityUserDetailsBinding

    @Inject
    lateinit var db: AppDB

    @Inject
    lateinit var viewModel: IUserDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_details)
        intent.extras?.getString(Keys.USERNAME)?.let { username ->
            loadUserByUsername(username)
        }
        setObservers()
    }

    private fun loadUserByUsername(username: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.loadUserByUsername(username)
        }
    }

    private fun setObservers() {
        (viewModel as UserDetailsViewModelImpl).loadUserByUsernameObserver.observe(this) { either ->
            either.fold(ifLeft = {
                Snackbar.make(binding.root, it?.message ?: "Unknown error", Snackbar.LENGTH_LONG).show()
            }, ifRight = {
                onUserFetched(it)
            })
        }
    }

    private fun onUserFetched(user: User) {
        fillUserData(user)
    }

    private fun fillUserData(user: User) {
        Glide.with(this).load(user.avatar_url).placeholder(R.drawable.ic_baseline_account_circle_24)
            .error(R.drawable.ic_baseline_account_circle_24)
            .into(binding.avatarUrl)
        binding.login.text = "Username: ${user.login}"
        user.login?.let { binding.login.visibility = View.VISIBLE }
        binding.fullName.text = "Full Name: ${user.name}"
        user.name?.let { binding.fullName.visibility = View.VISIBLE }
        binding.followers.text = "Followers: ${user.followers}"
        user.followers?.let { binding.followers.visibility = View.VISIBLE }
        binding.followings.text = "Followings: ${user.following}"
        user.following?.let { binding.followings.visibility = View.VISIBLE }
    }
}