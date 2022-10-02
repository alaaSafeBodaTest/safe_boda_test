package com.example.safebodatest.features.user_details.presentation.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.safebodatest.features.user_details.presentation.view.UserFollowersFragment
import com.example.safebodatest.features.user_details.presentation.view.UserFollowingFragment

class UserFollowViewPagerAdapter(val username: String? = null, activity: AppCompatActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if(position == 0) UserFollowersFragment(username) else UserFollowingFragment(username)
    }
}