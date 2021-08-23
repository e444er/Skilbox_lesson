package com.devv.roomdao.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.devv.roomdao.R
import com.devv.roomdao.adapter.UserAdapter
import com.devv.roomdao.databinding.UserFragmentBinding
import com.devv.roomdao.db.User
import com.devv.roomdao.di.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment(R.layout.user_fragment) {

    private lateinit var binding: UserFragmentBinding
    private val viewModel: UserViewModel by viewModels{ ViewModelFactory() }
    private var userAdapter: UserAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = UserFragmentBinding.bind(view)
        initList()
        bindViewModel()
        click()
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_userFragment_to_addFragment)
        }
    }

    private fun click() {
        userAdapter?.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(id: User) {
                val action = UserFragmentDirections.actionUserFragmentToUpdateFragment(id)
                findNavController().navigate(action)
            }
        })
        userAdapter?.setonItemClickDelete(object : UserAdapter.OnItemClickDelete {
            override fun onItemDelete(user: User) {
                viewModel.deleteUsers(user)
            }
        })
    }

    private fun bindViewModel() {
        viewModel.readAllData.observe(viewLifecycleOwner, { userAdapter?.differ?.submitList(it) })
    }

    private fun initList() {
        userAdapter = UserAdapter()
        with(binding.recyclerview) {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        userAdapter = null
    }
}
