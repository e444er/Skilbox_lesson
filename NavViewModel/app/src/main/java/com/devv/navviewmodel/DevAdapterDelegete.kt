package com.devv.navviewmodel

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class DevAdapterDelegete(private val onItemClick: (id: Long) -> Unit) :
    AbsListItemAdapterDelegate<Person.Developer, Person, DevAdapterDelegete.DeveloperHolder>() {

    override fun isForViewType(item: Person, items: MutableList<Person>, position: Int): Boolean {
        return item is Person.Developer
    }

    override fun onCreateViewHolder(parent: ViewGroup): DeveloperHolder {
        return DeveloperHolder(parent.inflate(R.layout.item_dev), onItemClick)
    }

    override fun onBindViewHolder(
        item: Person.Developer,
        holder: DeveloperHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class DeveloperHolder(
        view: View,
        onItemClick: (id: Long) -> Unit,
    ) : BasePerson(view, onItemClick) {
        private val proTextView: TextView =
            view.findViewById(R.id.protextView3)

        fun bind(person: Person.Developer) {
            bindMain(person.name, person.avatarLink, person.age)
            proTextView.text = person.programLong

        }
    }
}