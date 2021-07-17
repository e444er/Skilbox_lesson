package com.devv.networking

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

abstract class MovieView(view: View) : RecyclerView.ViewHolder(view) {

    private val title: TextView = view.findViewById(R.id.textView)
    private val description: TextView = view.findViewById(R.id.textView2)
    private val type: TextView = view.findViewById(R.id.textView3)
    private val poster: ImageView = view.findViewById(R.id.imageView)
    protected fun bind(
        titleb: String,
        des: String,
        typee: String,
        posterr: String
    ) {
        title.text = titleb
        description.text = des
        type.text = typee
        Glide.with(itemView)
            .load(posterr)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(poster)
    }
}
