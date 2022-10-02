package com.example.safebodatest.features.search_user.presentation.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.safebodatest.R
import com.example.safebodatest.core.consts.Keys
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.databinding.ActivitySearchUserBinding
import com.example.safebodatest.features.search_user.presentation.view_model.ISearchUserViewModel
import com.example.safebodatest.features.search_user.presentation.view_model.SearchUserViewModelImpl
import com.example.safebodatest.features.user_details.presentation.view.UserDetailsActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchUserActivity : AppCompatActivity() {

    lateinit var binding: ActivitySearchUserBinding

    @Inject
    lateinit var viewModel: ISearchUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_user)
        setObservers()
    }

    private fun setObservers() {
        (viewModel as SearchUserViewModelImpl).searchObserver.observe(this) { either ->
            either.fold(ifLeft = {
                searchFailed(it)
            }, ifRight = { user ->
                user.login?.let { username ->
                    binding.searchImage.visibility = View.VISIBLE
                    binding.notFoundImage.visibility = View.GONE
                    goToUserDetails(username)
                }
            })
        }
        binding.searchFab.setOnClickListener {
            if(binding.searchBar.text.toString().isNotEmpty())
                searchFor()
            else
                binding.searchBar.error = getString(R.string.field_required)
        }
    }

    private fun goToUserDetails(username: String) {
        val intent = Intent(this, UserDetailsActivity::class.java)
        intent.putExtra(Keys.USERNAME, username)
        startActivity(intent)
        finish()
    }

    private fun searchFailed(it: IFailure?) {
        Snackbar.make(binding.root, it?.message ?: "", Snackbar.LENGTH_LONG).show()
        binding.searchImage.visibility = View.GONE
        binding.notFoundImage.visibility = View.VISIBLE
    }

    private fun searchFor() {
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.searchForUser(binding.searchBar.text.toString())
        }
    }
}