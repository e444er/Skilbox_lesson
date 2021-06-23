package com.devv.lists_2

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    private var images: List<String> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapter.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_image))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size

    fun setImage(newImages: List<String>) {
        images = newImages
        notifyDataSetChanged()
    }

    class ViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {
        private val image = containerView.findViewById<ImageView>(R.id.imageView11)

        fun bind(imageurl: String) = with(itemView) {
            Glide.with(itemView)
                .load(imageurl)
                .placeholder(R.drawable.ic_add_24)
                .into(image)
            //не работаеть LayoutContainer
        }
    }
}