package com.devv.networking

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

abstract class MovieView(view: View) : RecyclerView.ViewHolder(view) {

    private val title: TextView = view.findViewById(R.id.textView)
    private val description: TextView = view.findViewById(R.id.textView2)
    protected fun bind(
        titleb: String,
        des: String,
    ) {
        title.text = titleb
        description.text = des
    }
}