package com.devv.scopedstoragee.test

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.devv.scopedstoragee.R
import com.devv.scopedstoragee.databinding.AddFragmentBinding

class AddFragment : Fragment(R.layout.add_fragment) {

    private val binding: AddFragmentBinding by viewBinding(AddFragmentBinding::bind)
    private val viewModel: ImageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button3.setOnClickListener {
            save()
        }
    }

    private fun save() {
        val eUrl = binding.editTextUrl.text.toString()
        val eName = binding.editTextTextPersonName.text.toString()
        viewModel.saveImages(eName, eUrl)
        findNavController().navigate(R.id.action_addFragment_to_testFragment)
    }
}