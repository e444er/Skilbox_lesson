package com.devv.broadcastreceiver

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.devv.broadcastreceiver.databinding.MainFragmentBinding

class MainFragment : Fragment(R.layout.main_fragment) {

    private val binding: MainFragmentBinding by viewBinding(MainFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.firebaseButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_firebaseFragment)
        }
        binding.notificationButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_notifaFragment)
        }
    }
}