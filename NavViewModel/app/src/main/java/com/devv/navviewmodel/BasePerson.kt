package com.devv.navviewmodel

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

abstract class BasePerson(
    view: View,
    onItemClick: (id: Long) -> Unit,
) : RecyclerView.ViewHolder(view) {

    private val nameTextView: TextView = view.findViewById(R.id.nameTextView)
    private val ageTextView: TextView = view.findViewById(R.id.ageTextView2)
    private val avatarImage: ImageView = view.findViewById(R.id.avatarImage)

    init {
        view.setOnClickListener {
            onItemClick(adapterPosition.toLong())
        }
//            view.setOnLongClickListener {
//                Toast.makeText(this,"This is a long click", Toast.LENGTH_SHORT).show();
//                true
//            }
    }

    protected fun bindMain(
        name: String,
        avatarLink: String,
        age: Int,
    ) {
        nameTextView.text = name
        ageTextView.text = "Возраст = ${age}"
        Glide.with(itemView)
            .load(avatarLink)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(avatarImage)
    }
}