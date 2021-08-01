package com.devv.roomdao.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devv.roomdao.R
import com.devv.roomdao.databinding.UserListBinding
import com.devv.roomdao.db.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    class UserViewHolder(val binding: UserListBinding) : RecyclerView.ViewHolder(binding.root) {}

    private var onItemClickCallback: OnItemClickCallback? = null
    private var onItemClickDelete: OnItemClickDelete? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setonItemClickDelete(onItemClickDelete: OnItemClickDelete) {
        this.onItemClickDelete = onItemClickDelete
    }

    class DifferCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer<User>(this, DifferCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        with(holder) {
            val user = differ.currentList[position]
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }
            itemView.apply {
                binding.name.text = user.firstName
                binding.lastName.text = user.lastName
                binding.emailName.text = user.emailName
                Glide.with(this).load(user.avatar).placeholder(R.drawable.ic_baseline_image_24)
                    .into(binding.imageView)
                binding.deleteImageView.setOnClickListener {
                    onItemClickDelete?.onItemDelete(user)
                }
            }
        }

    }

    override fun getItemCount() = differ.currentList.size

    interface OnItemClickCallback {
        fun onItemClicked(id: User)
    }

    interface OnItemClickDelete {
        fun onItemDelete(user: User)
    }


}