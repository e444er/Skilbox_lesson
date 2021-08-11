package com.devv.flow.lesson

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.devv.flow.R
import com.devv.flow.databinding.BasicFragmentBinding

class BasicFragment : Fragment(R.layout.basic_fragment) {

    private val binding: BasicFragmentBinding by viewBinding(BasicFragmentBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFlow.setOnClickListener {
            findNavController().navigate(BasicFragmentDirections.actionBasicFragmentToFlowBasicFragment())
        }

        binding.buttonEdit.setOnClickListener {
            findNavController().navigate(BasicFragmentDirections.actionBasicFragmentToEditTwoFragment())
        }

        binding.buttonThree.setOnClickListener {
            findNavController().navigate(BasicFragmentDirections.actionBasicFragmentToThreeFragment())
        }
        binding.homework.setOnClickListener {
            findNavController().navigate(BasicFragmentDirections.actionBasicFragmentToMovieFragment())
        }
    }
}