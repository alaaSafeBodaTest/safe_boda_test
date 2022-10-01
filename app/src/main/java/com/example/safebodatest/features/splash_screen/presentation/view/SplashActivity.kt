package com.example.safebodatest.features.splash_screen.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import arrow.core.Either
import com.example.safebodatest.R
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.network_utils.NetworkUtils
import com.example.safebodatest.databinding.ActivitySplashBinding
import com.example.safebodatest.features.login.presentation.view.SignInActivity
import com.example.safebodatest.features.splash_screen.presentation.view_holder.ISplashViewModel
import com.example.safebodatest.features.splash_screen.presentation.view_holder.SplashViewModel
import com.example.safebodatest.features.users_list.presentation.view.UsersListActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding

    @Inject
    lateinit var viewModel: ISplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        setObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.hasToken()
    }

    private fun setObservers() {
        (viewModel as SplashViewModel).getTokenObserver.observe(this) { either ->
            handleToken(either)
        }
        (viewModel as SplashViewModel).getUserObserver.observe(this) { either ->
            either.fold(ifLeft = { iFailure ->
                handleNoUser(iFailure)
            },
                ifRight = { safeUser ->
                    onUserFetched(safeUser)
                }
            )
        }
        (viewModel as SplashViewModel).storeUser.observe(this) { either ->
            either.fold(ifLeft = { iFailure ->
                if (iFailure != null) {
                    storeUserFailed(iFailure)
                }
            }, {
                Log.e(javaClass.simpleName, "setObservers: User is Stored")
                goToUsersListActivity()
            }
            )
        }
    }

    private fun goToUsersListActivity() {
        val intent = Intent(this, UsersListActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }

    private fun onUserFetched(safeUser: User) {
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.storeUser(safeUser)
        }
    }

    private fun storeUserFailed(iFailure: IFailure) {
        Toast.makeText(this, iFailure.message, Toast.LENGTH_SHORT).show()
        goToSignIn()
    }

    private fun handleNoUser(iFailure: IFailure?) {
        Snackbar.make(binding.root, getString(R.string.something_went_wrong), Snackbar.LENGTH_LONG)
            .show()
        Log.e(javaClass.simpleName, "handleNoUser: ${iFailure?.message}")
        goToSignIn()
    }

    private fun handleToken(either: Either<IFailure?, String>) {
        either.fold(ifLeft = {
            Log.e(javaClass.simpleName, "handleToken: ${it?.message}")
            goToSignIn()
        },
            ifRight = { token ->
                lifecycleScope.launch(Dispatchers.IO) {
                    viewModel.fetchUser(NetworkUtils.hasInternetConnection(this@SplashActivity))
                }
            })
    }

    private fun goToSignIn() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }
}