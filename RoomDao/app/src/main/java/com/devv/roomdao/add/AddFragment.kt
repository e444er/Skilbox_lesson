package com.devv.roomdao.add

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devv.roomdao.R
import com.devv.roomdao.databinding.AddFragmentBinding
import com.devv.roomdao.db.User
import com.devv.roomdao.di.ViewModelFactory
import com.devv.roomdao.list.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFragment : Fragment(R.layout.add_fragment) {
    private var _binding: AddFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserViewModel by viewModels{ ViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = AddFragmentBinding.bind(view)
        binding.button.setOnClickListener {
            save()
        }
    }

    private fun save() {
        val firstName = binding.editName.text.toString().trim()
        val lastName = binding.editLastName.text.toString().trim()
        val emailName = binding.editEmailName.text.toString().trim()
        val avatar = binding.editAvatar.text.toString().trim()
        val age =6
        if (firstName.isNotEmpty()) {
            val user = User(0, firstName, lastName, emailName, avatar,age = age)
            viewModel.saveUsers(user)
            Toast.makeText(requireContext(), "Save", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_userFragment)
        } else {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}