package com.devv.lists_2


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.devv.lists_2.databinding.ListFragmentBinding
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import kotlin.random.Random

class PersonListFragment : Fragment(R.layout.list_fragment) {

    private lateinit var binding: ListFragmentBinding

    private var persons = listOf(
        Person.Developer(
            1,
            name = "Иван Петров",
            avatarLink = "https://cdn.pixabay.com/photo/2013/10/22/07/56/android-199225_960_720.jpg",
            age = 34,
            programLong = "java"
        ),
        Person.User(
            2,
            name = "Саша Иванов",
            avatarLink = "https://cdn.pixabay.com/photo/2013/10/22/07/56/android-199225_960_720.jpg",
            age = 19
        ),
        Person.Developer(
            3,
            name = "Тор Молот",
            avatarLink = "https://cdn.pixabay.com/photo/2013/10/22/07/56/android-199225_960_720.jpg",
            age = 26,
            programLong = "kotlin"
        ),
        Person.User(
            4,
            name = "Халк зеленый",
            avatarLink = "https://cdn.pixabay.com/photo/2013/10/22/07/56/android-199225_960_720.jpg",
            age = 44,
        ),
        Person.Developer(
            5,
            name = "Вдова Черный",
            avatarLink = "https://cdn.pixabay.com/photo/2013/10/22/07/56/android-199225_960_720.jpg",
            age = 25,
            programLong = "python"
        ),
        Person.User(
            6,
            name = "Пантера Черный",
            avatarLink = "https://cdn.pixabay.com/photo/2013/10/22/07/56/android-199225_960_720.jpg",
            age = 27,
        ),
        Person.Developer(
            7,
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
        personAdapter?.items = (persons)
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
            itemAnimator = SlideInLeftAnimator()
        }
    }

    private fun deletePerson(position: Int) {
        persons = persons.filterIndexed { index, user -> index != position }
        personAdapter?.items = (persons)
    }

    private fun adduser() {
        val newUser = persons.random().let {
            when (it) {
                is Person.Developer -> it.copy(id = Random.nextLong())
                is Person.User -> it.copy(id = Random.nextLong())
            }
        }
        persons = listOf(newUser) + persons
        personAdapter?.items = (persons)
        binding.userList.scrollToPosition(0)
    }

}