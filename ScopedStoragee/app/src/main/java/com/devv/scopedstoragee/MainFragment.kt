package com.devv.scopedstoragee

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.devv.scopedstoragee.databinding.MainFragmnetBinding

class MainFragment : Fragment(R.layout.main_fragmnet) {

    private val binding: MainFragmnetBinding by viewBinding(MainFragmnetBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_testFragment)
        }
        binding.button4.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_docFragment)
        }
    }
}