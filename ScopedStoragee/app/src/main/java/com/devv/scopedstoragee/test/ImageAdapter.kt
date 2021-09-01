package com.devv.scopedstoragee.test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devv.scopedstoragee.databinding.ItemPhotoBinding

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    inner class ImageViewHolder(val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root)

    private class DiffUtilCallback : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, DiffUtilCallback())

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ImageAdapter.ImageViewHolder {
        return ImageViewHolder(ItemPhotoBinding.inflate
            (LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ImageAdapter.ImageViewHolder, position: Int) {
        val image = differ.currentList[position]
        holder.binding.apply {
            imageView.setImageURI(image.uri)
            root.setOnClickListener {
                onItemClickCallback?.onItemClicked(image)
            }
            textView.text = image.name
            Glide.with(root)
                .load(image.uri)
                .into(imageView)

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Image)
    }

}