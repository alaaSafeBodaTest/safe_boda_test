package com.example.safebodatest.features.user_details.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.safebodatest.R
import com.example.safebodatest.databinding.FragmentUserFollowersBinding

class UserFollowersFragment(val username: String? = null) : Fragment() {

    lateinit var binding: FragmentUserFollowersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_user_followers, container, false)

        return binding.root
    }

}