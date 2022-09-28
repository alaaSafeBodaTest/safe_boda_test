package com.example.safebodatest.core.utils

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider
import javax.inject.Inject

class GithubUtils @Inject constructor() {

    private val provider = OAuthProvider.newBuilder("github.com")

    private val auth = FirebaseAuth.getInstance()

    val authResultObserver = MutableLiveData<Task<AuthResult>>()

    fun signInWithGithubProvider(activity: Activity, email:String? = null) {
        val pendingResultTask: Task<AuthResult>? = auth.pendingAuthResult
        email?.let {
            provider.addCustomParameter("login",it)
        }
        if (pendingResultTask != null) {
            pendingResultTask
                .addOnCompleteListener {
                    authResultObserver.postValue(it)
                }
        } else {
            auth.startActivityForSignInWithProvider(activity, provider.build())
                .addOnCompleteListener {
                    authResultObserver.postValue(it)
                }
        }

    }

}