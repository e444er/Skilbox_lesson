package com.devv.lists_2

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class UserAdapterDelegate(private val onItemClick: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<Person.User, Person, UserHolder>() {

    override fun isForViewType(item: Person, items: MutableList<Person>, position: Int): Boolean {
        return item is Person.User
    }

    override fun onCreateViewHolder(parent: ViewGroup): UserHolder {
        return UserHolder(parent.inflate(R.layout.item_user), onItemClick)
    }

    override fun onBindViewHolder(
        item: Person.User,
        holder: UserHolder,
        payloads: MutableList<Any>,
    ) {
        holder.bind(item)
    }
}

class UserHolder(
    view: View,
    onItemClick: (position: Int) -> Unit,
) : BasePerson(view, onItemClick) {
    init {
        view.findViewById<TextView>(R.id.devtextView3).isVisible = false
    }

    fun bind(person: Person.User) {
        bindMain(person.name, person.avatarLink, person.age)
    }
}
