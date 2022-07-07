@file:Suppress("UnusedImport")

package com.juned.expertclass.suitmediainterntest.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.juned.expertclass.suitmediainterntest.data.remote.UserDataResponse
import com.juned.expertclass.suitmediainterntest.databinding.ItemListUserBinding
import com.juned.expertclass.suitmediainterntest.ui.screen2.ScreenTwoActivity

class ListUsersAdapter(private val onClick: (String) -> Unit) : PagingDataAdapter<UserDataResponse, ListUsersAdapter.ListUsersViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUsersViewHolder {
        val binding = ItemListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListUsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListUsersViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
            holder.itemView.setOnClickListener {
                onClick(data.firstName + ""+data.lastName)
            }

        }
    }

    class ListUsersViewHolder(private val binding: ItemListUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: UserDataResponse) {
            Glide.with(this.itemView.context)
                .load(data.avatar)
                .circleCrop()
                .into(binding.imgItemPhoto)
            binding.tvItemName.text = "${data.firstName} ${data.lastName}"
            binding.tvItemEmail.text = data.email
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserDataResponse>() {
            override fun areItemsTheSame(
                oldItem: UserDataResponse,
                newItem: UserDataResponse
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: UserDataResponse,
                newItem: UserDataResponse
            ): Boolean {
                return oldItem.email == newItem.email
            }
        }
    }
}