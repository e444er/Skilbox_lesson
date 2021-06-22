package com.devv.lists_1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.devv.lists_1.databinding.ListFragmentBinding

class PersonListFragment : Fragment(R.layout.list_fragment) {

    private lateinit var binding: ListFragmentBinding

    private var persons = listOf(
        Person.Developer(
            name = "Иван Петров",
            avatarLink = "https://cdn.pixabay.com/photo/2013/10/22/07/56/android-199225_960_720.jpg",
            age = 34,
            programLong = "java"
        ),
        Person.User(
            name = "Саша Иванов",
            avatarLink = "https://cdn.pixabay.com/photo/2013/10/22/07/56/android-199225_960_720.jpg",
            age = 19
        ),
        Person.Developer(
            name = "Тор Молот",
            avatarLink = "https://cdn.pixabay.com/photo/2013/10/22/07/56/android-199225_960_720.jpg",
            age = 26,
            programLong = "kotlin"
        ),
        Person.User(
            name = "Халк зеленый",
            avatarLink = "https://cdn.pixabay.com/photo/2013/10/22/07/56/android-199225_960_720.jpg",
            age = 44,
        ),
        Person.Developer(
            name = "Вдова Черный",
            avatarLink = "https://cdn.pixabay.com/photo/2013/10/22/07/56/android-199225_960_720.jpg",
            age = 25,
            programLong = "python"
        ),
        Person.User(
            name = "Пантера Черный",
            avatarLink = "https://cdn.pixabay.com/photo/2013/10/22/07/56/android-199225_960_720.jpg",
            age = 27,
        ),
        Person.Developer(
            name = "Танос Химия",
            avatarLink = "https://cdn.pixabay.com/photo/2013/10/22/07/56/android-199225_960_720.jpg",
            age = 23,
            programLong = "C++"
        ),
    )

    private var personAdapter: PersonAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ListFragmentBinding.bind(view)
        initList()
        binding.floatingActionButton.setOnClickListener { adduser() }
        personAdapter?.updatePersons(persons)
        personAdapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        personAdapter = null
    }

    private fun initList() {
        personAdapter = PersonAdapter { position -> deletePerson(position) }
        with(binding.userList) {
            adapter = personAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun deletePerson(position: Int) {
        persons = persons.filterIndexed { index, user -> index != position }
        personAdapter?.updatePersons(persons)
        personAdapter?.notifyItemRemoved(position)
    }

    private fun adduser() {
        val newUser = persons.random()
        persons = listOf(newUser) + persons
        personAdapter?.updatePersons(persons)
        personAdapter?.notifyItemInserted(0)
        binding.userList.scrollToPosition(0)
    }
}