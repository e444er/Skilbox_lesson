package com.devv.roomdao

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.devv.roomdao.databinding.MainFragmentBinding

class MainFragment : Fragment(R.layout.main_fragment) {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = MainFragmentBinding.bind(view)
        binding.button2.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment2_to_userFragment)
        }
        binding.button3.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment2_to_priceFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}