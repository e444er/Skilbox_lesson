package com.devv.lists_1

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class UserAdapter(
    private val onItemClicked: (position: Int) -> Unit
) : RecyclerView.Adapter<UserAdapter.Holder>() {

    private var users: List<User> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
       return Holder(parent.inflate(R.layout.item_user), onItemClicked)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    fun updateUsers(newUsers: List<User>) {
        users = newUsers
    }

    override fun getItemCount(): Int = users.size


    class Holder(view: View,
                 onItemClicked: (position: Int) -> Unit) : RecyclerView.ViewHolder(view) {

        private val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        private val ageTextView: TextView = view.findViewById(R.id.ageTextView2)
        private val devTextView: TextView = view.findViewById(R.id.devtextView3)
        private val avatarImage: ImageView = view.findViewById(R.id.avatarImage)

        init {
            view.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }

        fun bind(user: User) {
            nameTextView.text = user.name
            ageTextView.text = "Возраст = ${user.age}"
            devTextView.visibility = if (user.isDeveloper) View.VISIBLE else View.GONE
            Glide.with(itemView)
                .load(user.avatarLink)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(avatarImage)
        }
    }
}