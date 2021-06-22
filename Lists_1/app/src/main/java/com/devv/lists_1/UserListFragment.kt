package com.devv.lists_1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.devv.lists_1.databinding.ListFragmentBinding

class UserListFragment : Fragment(R.layout.list_fragment) {

    private lateinit var binding: ListFragmentBinding

    private var users = listOf(
        User(
            name = "Иван Петров",
            avatarLink = "https://cdn.pixabay.com/photo/2013/10/22/07/56/android-199225_960_720.jpg",
            age = 34,
            isDeveloper = true
        ),
        User(
            name = "Саша Иванов",
            avatarLink = "https://cdn.pixabay.com/photo/2013/10/22/07/56/android-199225_960_720.jpg",
            age = 19,
            isDeveloper = false
        ),
        User(
            name = "Тор Молот",
            avatarLink = "https://cdn.pixabay.com/photo/2013/10/22/07/56/android-199225_960_720.jpg",
            age = 26,
            isDeveloper = true
        ),
        User(
            name = "Халк зеленый",
            avatarLink = "https://cdn.pixabay.com/photo/2013/10/22/07/56/android-199225_960_720.jpg",
            age = 44,
            isDeveloper = false
        ),
        User(
            name = "Вдова Черный",
            avatarLink = "https://cdn.pixabay.com/photo/2013/10/22/07/56/android-199225_960_720.jpg",
            age = 25,
            isDeveloper = true
        ),
        User(
            name = "Пантера Черный",
            avatarLink = "https://cdn.pixabay.com/photo/2013/10/22/07/56/android-199225_960_720.jpg",
            age = 27,
            isDeveloper = false
        ),
        User(
            name = "Танос Химия",
            avatarLink = "https://cdn.pixabay.com/photo/2013/10/22/07/56/android-199225_960_720.jpg",
            age = 23,
            isDeveloper = true
        ),
    )

    private var userAdapter: UserAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ListFragmentBinding.bind(view)
        initList()
        binding.floatingActionButton.setOnClickListener { adduser() }
        userAdapter?.updateUsers(users)
        userAdapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        userAdapter = null
    }

    private fun initList() {
        userAdapter = UserAdapter { position -> deleteUser(position) }
        with(binding.userList) {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun deleteUser(position: Int) {
        users = users.filterIndexed { index, user -> index != position }
        userAdapter?.updateUsers(users)
        userAdapter?.notifyItemRemoved(position)
    }

    private fun adduser() {
        val newUser = users.random()
        users = listOf(newUser) + users
        userAdapter?.updateUsers(users)
        userAdapter?.notifyItemInserted(0)
        binding.userList.scrollToPosition(0)
    }
}