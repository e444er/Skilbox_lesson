package com.devv.navviewmodel


import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class PersonAdapter(
    private val onItemClick: (id: Long) -> Unit,
) : AsyncListDifferDelegationAdapter<Person>(PersonDiffUtil()) {

    init {
        delegatesManager.addDelegate(UserAdapterDelegete(onItemClick))
            .addDelegate(DevAdapterDelegete(onItemClick))
    }

    class PersonDiffUtil : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return when {
                oldItem is Person.Developer && newItem is Person.Developer -> oldItem.id == newItem.id
                oldItem is Person.User && newItem is Person.User -> oldItem.id == newItem.id
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }

    }
}