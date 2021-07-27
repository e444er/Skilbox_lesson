package com.devv.contentprovider


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.devv.contentprovider.databinding.ListItemBinding

class ListAdapter() :
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    class ListViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    class DiffUtilCallBack : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer<Contact>(this, DiffUtilCallBack())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        with(holder) {
            val user = differ.currentList[position]
            itemView.apply {
                binding.name.text = user.name
                binding.number.text = user.number.toString()
            }
        }
    }

    override fun getItemCount() = differ.currentList.size
}