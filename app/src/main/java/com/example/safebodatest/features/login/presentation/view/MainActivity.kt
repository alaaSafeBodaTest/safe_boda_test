package com.example.safebodatest.features.login.presentation.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.safebodatest.R
import com.example.safebodatest.core.network_utils.NetworkUtils
import com.example.safebodatest.core.preferences.PreferenceManager
import com.example.safebodatest.core.utils.GithubUtils
import com.example.safebodatest.databinding.ActivityMainBinding
import com.example.safebodatest.features.login.presentation.actions.LoginActions
import com.example.safebodatest.features.login.presentation.view_models.ILoginViewModel
import com.example.safebodatest.features.login.presentation.view_models.LoginViewModel
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.OAuthCredential
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModel: ILoginViewModel

    @Inject
    lateinit var githubUtils: GithubUtils

    @Inject
    lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        setObservers()
    }

    private fun setObservers() {

        (viewModel as LoginViewModel).actionObserver.observe(this) {
            when (it) {
                LoginActions.SIGN_IN_WITH_GITHUB -> {
                    onGithubSignInClicked()
                }
                null -> {}
            }
        }

        githubUtils.authResultObserver.observe(this) {
            onGithubResponse(it)
        }

        (viewModel as LoginViewModel).storeTokenObserver.observe(this) {
            if (it) {
                onSuccessfulSavingToken()
            } else {
                onFailedSavingToken()
            }
        }

        (viewModel as LoginViewModel).fetchedUserObserver.observe(this) {
            Log.e(javaClass.simpleName, "setObservers: $it", )
        }
    }

    private fun onFailedSavingToken() {
        Snackbar.make(binding.root, "Something went wrong", Snackbar.LENGTH_LONG).show()
    }

    private fun onSuccessfulSavingToken() {
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.fetchUserAccount()
        }
    }

    private fun onGithubSignInClicked() {
        if (NetworkUtils.hasInternetConnection(this))
            githubUtils.signInWithGithubProvider(this)
        else {
            Snackbar.make(binding.root, getString(R.string.internet_required), Snackbar.LENGTH_LONG)
                .show()
        }
    }

    private fun onGithubResponse(it: Task<AuthResult>) {
        if (it.isSuccessful) {
            onSuccessfulGithubLogin(it)
        } else {
            onFailedGithubLogin(it)
        }
    }

    private fun onFailedGithubLogin(it: Task<AuthResult>) {
        Toast.makeText(
            this,
            it.exception?.localizedMessage ?: "Unknown Error",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun onSuccessfulGithubLogin(it: Task<AuthResult>) {
        val token = ((it.result.credential as OAuthCredential?)?.accessToken)
        token?.let { safeToken ->
            viewModel.storeToken(safeToken)
        }
    }
}