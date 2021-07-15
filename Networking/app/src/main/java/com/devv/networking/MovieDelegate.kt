package com.devv.networking

import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class MovieDelegate :
    AbsListItemAdapterDelegate<RemateMovie, RemateMovie, MovieDelegate.MovieHolder>() {

    override fun isForViewType(
        item: RemateMovie,
        items: MutableList<RemateMovie>,
        position: Int
    ): Boolean {
        return item is RemateMovie
    }

    override fun onCreateViewHolder(parent: ViewGroup): MovieHolder {
        return MovieHolder(parent.inflate(R.layout.movie_list))
    }

    override fun onBindViewHolder(
        item: RemateMovie,
        holder: MovieHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class MovieHolder(view: View) : MovieView(view) {

        fun bind(remateMovie: RemateMovie){
            bind(remateMovie.title,remateMovie.year)
        }
    }
}