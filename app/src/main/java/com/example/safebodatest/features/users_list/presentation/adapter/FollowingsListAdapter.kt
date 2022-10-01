package com.example.safebodatest.features.users_list.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.safebodatest.R
import com.example.safebodatest.databinding.FollowingListItemBinding
import com.example.safebodatest.features.users_list.presentation.data_holder.FollowingListItem
import javax.inject.Inject

class FollowingsListAdapter @Inject constructor() :
    RecyclerView.Adapter<FollowingsListAdapter.FollowingsListViewHolder>() {

    private val list = mutableListOf<FollowingListItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(usersList: List<FollowingListItem>) {
        list.clear()
        list.addAll(usersList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingsListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<FollowingListItemBinding>(
            inflater,
            R.layout.following_list_item,
            parent,
            false
        )
        return FollowingsListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowingsListViewHolder, position: Int) {
        val item = list[position]
        val binding = holder.binding
        binding.user = item
        Glide.with(binding.root.context)
            .load(item.imgUrl)
            .placeholder(R.drawable.ic_baseline_account_circle_24)
            .error(R.drawable.ic_baseline_account_circle_24)
            .into(binding.image)
    }

    override fun getItemCount(): Int = list.size

    inner class FollowingsListViewHolder(val binding: FollowingListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}