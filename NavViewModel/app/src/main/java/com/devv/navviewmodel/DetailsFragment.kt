package com.devv.navviewmodel

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.devv.navviewmodel.databinding.DdetailsFragmentBinding

class DetailsFragment : Fragment(R.layout.ddetails_fragment) {

    private lateinit var binding: DdetailsFragmentBinding
    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DdetailsFragmentBinding.bind(view)
        binding.textView2.text = args.person.toString()
    }
}