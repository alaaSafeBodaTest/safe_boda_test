package com.example.safebodatest.features.users_list.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.safebodatest.R
import com.example.safebodatest.databinding.UsersListItemBinding
import com.example.safebodatest.features.users_list.presentation.data_holder.UserListItem

class UsersListAdapter : RecyclerView.Adapter<UsersListAdapter.UsersListViewHolder>() {

    val list = mutableListOf<UserListItem>()

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
        holder.binding.user = item
    }

    override fun getItemCount(): Int = list.size

    inner class UsersListViewHolder(val binding: UsersListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}