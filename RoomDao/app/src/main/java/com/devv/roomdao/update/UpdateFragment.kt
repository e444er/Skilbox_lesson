package com.devv.roomdao.update

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.devv.roomdao.R
import com.devv.roomdao.databinding.UpdateFragmentBinding
import com.devv.roomdao.db.User
import com.devv.roomdao.di.ViewModelFactory
import com.devv.roomdao.list.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateFragment : Fragment(R.layout.update_fragment) {
    private var _binding: UpdateFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserViewModel by viewModels{ ViewModelFactory() }
    private val args: UpdateFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = UpdateFragmentBinding.bind(view)
        binding.editName.setText(args.listUser.firstName)
        binding.editLastName.setText(args.listUser.firstName)
        binding.editEmailName.setText(args.listUser.emailName)
        binding.editAvatar.setText(args.listUser.avatar)
        binding.button.setOnClickListener {
            save()
        }

    }

    private fun save() {
        val id = args.listUser.id
        val firstName = binding.editName.text.toString().trim()
        val lastName = binding.editLastName.text.toString().trim()
        val emailName = binding.editEmailName.text.toString().trim()
        val avatar = binding.editAvatar.text.toString().trim()
        val age =7
        if (firstName.isNotEmpty()) {
            val user = User(id, firstName, lastName, emailName, avatar,age =age)
            viewModel.updateUsers(user)
            Toast.makeText(requireContext(), "Update", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_userFragment)
        } else {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}