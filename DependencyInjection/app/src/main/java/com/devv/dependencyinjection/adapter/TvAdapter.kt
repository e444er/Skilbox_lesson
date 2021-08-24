package com.devv.dependencyinjection.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devv.dependencyinjection.databinding.TvShowLayoutItemBinding
import com.devv.dependencyinjection.models.TvShowItem

class TvAdapter:RecyclerView.Adapter<TvAdapter.ViewHolder>() {

    inner class ViewHolder(val binding:TvShowLayoutItemBinding):RecyclerView.ViewHolder(binding.root)

    private class DifferCallback : DiffUtil.ItemCallback<TvShowItem>() {
        override fun areItemsTheSame(oldItem: TvShowItem, newItem: TvShowItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvShowItem, newItem: TvShowItem): Boolean {
            return oldItem == newItem
        }
    }

     val differ = AsyncListDiffer(this, DifferCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvAdapter.ViewHolder {
        return ViewHolder(TvShowLayoutItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: TvAdapter.ViewHolder, position: Int) {
        val cur = differ.currentList[position]

        holder.binding.apply {
            textView.text = cur.name
            Glide.with(root)
                .load(cur.image.original)
                .into(imageView)
        }
    }
    override fun getItemCount() = differ.currentList.size
}