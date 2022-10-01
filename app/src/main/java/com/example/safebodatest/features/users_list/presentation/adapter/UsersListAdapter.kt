package com.example.safebodatest.features.users_list.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.safebodatest.R
import com.example.safebodatest.databinding.UsersListItemBinding
import com.example.safebodatest.features.users_list.presentation.data_holder.UserListItem
import javax.inject.Inject

class UsersListAdapter @Inject constructor() :
    RecyclerView.Adapter<UsersListAdapter.UsersListViewHolder>() {

    private val list = mutableListOf<UserListItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(usersList: List<UserListItem>) {
        list.clear()
        list.addAll(usersList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<UsersListItemBinding>(
            inflater,
            R.layout.users_list_item,
            parent,
            false
        )
        return UsersListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersListViewHolder, position: Int) {
        val item = list[position]
        val binding = holder.binding
        binding.user = item
        Glide.with(binding.root.context)
            .load(item.imgUrl)
            .error(R.drawable.ic_baseline_account_circle_24)
            .into(binding.image)
    }

    override fun getItemCount(): Int = list.size

    inner class UsersListViewHolder(val binding: UsersListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}