package com.devv.navviewmodel


import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PersonAdapter(
    private val onItemClick: (id: Long) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val differ = AsyncListDiffer<Person>(this, PersonDiffUtil())

    lateinit var items: List<Person>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_D -> UserHolder(parent.inflate(R.layout.item_user), onItemClick)
            TYPE_U -> UserHolder(parent.inflate(R.layout.item_dev), onItemClick)
            else -> error("error")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (differ.currentList[position]) {
            is Person.Developer -> TYPE_D
            is Person.User -> TYPE_U
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserHolder -> {
                val person = differ.currentList[position].let { it as? Person.User }
                    ?: error("error $position")
                holder.bind(person)
            }
            is DeveloperHolder -> {
                val person = differ.currentList[position].let { it as? Person.Developer }
                    ?: error("error $position")
                holder.bind(person)
            }
            else -> error("error")
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun updatePersons(newPersons: List<Person>) {
        differ.submitList(newPersons)
    }

    class PersonDiffUtil : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return when {
                oldItem is Person.Developer && newItem is Person.Developer -> oldItem.id == newItem.id
                oldItem is Person.User && newItem is Person.User -> oldItem.id == newItem.id
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }

    }

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
            //проверте заранее спасибо
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

    class UserHolder(
        view: View,
        onItemClick: (id: Long) -> Unit,
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
        onItemClick: (id: Long) -> Unit,
    ) : BasePerson(view, onItemClick) {
        private val proTextView: TextView =
            view.findViewById(R.id.protextView3)

        fun bind(person: Person.Developer) {
            bindMain(person.name, person.avatarLink, person.age)
            proTextView.text = person.programLong

        }
    }


    companion object {
        private const val TYPE_U = 1
        private const val TYPE_D = 2
    }
}