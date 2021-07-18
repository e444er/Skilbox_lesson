package com.devv.networking

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class MovieAdapter : AsyncListDifferDelegationAdapter<RemateMovie>(MovieDiffUtil()) {

    init {
        delegatesManager.addDelegate(MovieDelegate())
    }

    class MovieDiffUtil : DiffUtil.ItemCallback<RemateMovie>() {
        override fun areItemsTheSame(oldItem: RemateMovie, newItem: RemateMovie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RemateMovie, newItem: RemateMovie): Boolean {
            return oldItem == newItem
        }
    }
}