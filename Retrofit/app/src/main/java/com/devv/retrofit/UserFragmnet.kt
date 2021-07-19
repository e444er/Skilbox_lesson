package com.devv.retrofit

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.devv.retrofit.databinding.UserFragmentBinding

class UserFragmnet : Fragment(R.layout.user_fragment) {

    private lateinit var binding: UserFragmentBinding
    private val viewModel: UserViewModel by viewModels()
    private var userAdapter: UserAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = UserFragmentBinding.bind(view)
        initList()
        bindModel()
        userAdapter?.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ReamteUser) {
                val action = UserFragmnetDirections.actionUserFragmnetToDetailUserFargment(username = data.username)
                findNavController().navigate(action)
            }
        })
    }

    private fun initList() {
        userAdapter = UserAdapter()
        with(binding.recyclerview) {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun bindModel() {
        binding.button.setOnClickListener {
            val edit = binding.editText.text.toString()
            viewModel.search(edit)
        }
        viewModel.isLoading.observe(viewLifecycleOwner, ::updateState)
        viewModel.userList.observe(viewLifecycleOwner, { userAdapter?.differ?.submitList(it) })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        userAdapter = null
    }

    private fun updateState(isLoading: Boolean) {
        binding.recyclerview.isVisible = isLoading.not()
        binding.progressBar.isVisible = isLoading
        binding.button.isEnabled = isLoading.not()
    }
}