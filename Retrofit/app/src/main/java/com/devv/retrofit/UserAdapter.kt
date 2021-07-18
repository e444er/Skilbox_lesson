package com.devv.retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devv.retrofit.databinding.UserListBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    class UserViewHolder(val binding: UserListBinding) : RecyclerView.ViewHolder(binding.root) {}

    class DifferCallBack : DiffUtil.ItemCallback<ReamteUser>() {
        override fun areItemsTheSame(oldItem: ReamteUser, newItem: ReamteUser): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ReamteUser, newItem: ReamteUser): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer<ReamteUser>(this, DifferCallBack())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserListBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        with(holder) {
            val user = differ.currentList[position]
            itemView.apply {
                Glide.with(this).load(user.avatar).into(binding.imageView)
                binding.textView.text = user.username
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}