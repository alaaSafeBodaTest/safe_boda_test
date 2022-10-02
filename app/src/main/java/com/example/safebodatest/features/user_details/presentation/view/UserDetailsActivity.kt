package com.example.safebodatest.features.user_details.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.safebodatest.R
import com.example.safebodatest.core.consts.Keys
import com.example.safebodatest.core.db.AppDB
import com.example.safebodatest.databinding.ActivityUserDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class UserDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityUserDetailsBinding

    @Inject
    lateinit var db: AppDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_details)
        intent.extras?.getInt(Keys.USER_ID)?.let {
            lifecycleScope.launch(Dispatchers.IO){
                db.userDao().loadAllByIds(intArrayOf(it)).firstOrNull()?.let { user ->
                    binding.user = user
                }
            }
        }
        setObservers()
    }

    private fun setObservers() {

    }
}