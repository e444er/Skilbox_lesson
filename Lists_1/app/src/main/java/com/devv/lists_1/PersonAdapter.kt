package com.devv.lists_1

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devv.lists_1.Person.Developer

class PersonAdapter(
    private val onItemClick: (position: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var persons: List<Person> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_D -> UserHolder(parent.inflate(R.layout.item_user), onItemClick)
            TYPE_U -> UserHolder(parent.inflate(R.layout.item_dev), onItemClick)
            else -> error("error")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (persons[position]) {
            is Developer -> TYPE_D
            is Person.User -> TYPE_U
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is UserHolder -> {
                val  person = persons[position].let { it as? Person.User }
                    ?: error("error $position")
            holder.bind(person)
            }
            is DeveloperHolder -> {
                val  person = persons[position].let { it as? Person.Developer }
                    ?: error("error $position")
                holder.bind(person)
            }
            else -> error("error")
        }
    }

    override fun getItemCount(): Int = persons.size

    fun updatePersons(newPersons: List<Person>) {
        persons = newPersons
    }

    abstract class BasePerson(
        view: View,
        onItemClick: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {


        private val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        private val ageTextView: TextView = view.findViewById(R.id.ageTextView2)
        private val avatarImage: ImageView = view.findViewById(R.id.avatarImage)

        init {
            view.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }

        protected fun bindMain(
            name: String,
            avatarLink: String,
            age: Int
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

    class UserHolder(
        view: View,
        onItemClick: (position: Int) -> Unit
    ) : BasePerson(view, onItemClick) {
        init {
            view.findViewById<TextView>(R.id.devtextView3).isVisible = false
        }

        fun bind(person: Person.User) {
            bindMain(person.name, person.avatarLink, person.age)
        }
    }

    class DeveloperHolder(
        view: View,
        onItemClick: (position: Int) -> Unit
    ) : BasePerson(view, onItemClick) {
        private val proTextView: TextView =
            view.findViewById(R.id.protextView3)

        fun bind(person: Developer) {
            bindMain(person.name, person.avatarLink, person.age)
            proTextView.text = person.programLong

        }
    }


    companion object {
        private const val TYPE_U = 1
        private const val TYPE_D = 2
    }
}