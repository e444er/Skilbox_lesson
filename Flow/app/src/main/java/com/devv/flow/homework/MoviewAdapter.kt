package com.devv.flow.homework

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devv.flow.databinding.MovieItemBinding

class MoviewAdapter : RecyclerView.Adapter<MoviewAdapter.MovieHolder>() {

    class MovieHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root)

    private class DifferCallback : DiffUtil.ItemCallback<Search>() {
        override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem.imdbID == newItem.imdbID
        }

        override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer<Search>(this, DifferCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val data = differ.currentList[position]
        with(holder) {
            binding.textViewMovie.text = data.Title
            binding.textViewMovieT.text = data.Year
            Glide.with(binding.root)
                .load(data.Poster)
                .into(binding.imageView)

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}