package com.example.safebodatest.features.user_details.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.safebodatest.R
import com.example.safebodatest.databinding.FragmentUserFollowingBinding

class UserFollowingFragment(val username: String? = null) : Fragment() {

    lateinit var binding: FragmentUserFollowingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_user_following, container, false)

        return binding.root
    }
}