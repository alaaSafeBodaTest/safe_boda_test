package com.example.safebodatest.features.user_details.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.safebodatest.R
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.databinding.UsersListItemBinding
import javax.inject.Inject

class UserFollowListAdapter @Inject constructor() :
    RecyclerView.Adapter<UserFollowListAdapter.UserFollowListViewHolder>() {

    private val list = mutableListOf<User>()

    var isFollowing = false
    var isFollower = false

    fun setList(usersList: List<User>) {
        list.clear()
        addAll(usersList)
    }

    var listener: (User) -> Unit = {}

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(usersList: List<User>) {
        list.addAll(usersList)
        val map = HashMap<Int, User>()
        list.forEach {
            it.id.let { id -> map[id] = it}
        }
        list.clear()
        list.addAll(map.values)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserFollowListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<UsersListItemBinding>(
            inflater,
            R.layout.users_list_item,
            parent,
            false
        )
        return UserFollowListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserFollowListViewHolder, position: Int) {
        val item = list[position]
        val binding = holder.binding
        binding.root.setOnClickListener { listener.invoke(item) }
        binding.user = item
        binding.isFollower = isFollower
        binding.isFollowed = isFollowing
        Glide.with(binding.root.context)
            .load(item.avatar_url)
            .placeholder(R.drawable.ic_baseline_account_circle_24)
            .error(R.drawable.ic_baseline_account_circle_24)
            .into(binding.image)
    }

    override fun getItemCount(): Int = list.size

    inner class UserFollowListViewHolder(val binding: UsersListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}