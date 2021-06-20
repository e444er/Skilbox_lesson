package com.devv.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.devv.fragments.databinding.FragmentListBinding


class ListFragment : Fragment(R.layout.fragment_list) {


    private lateinit var binding: FragmentListBinding

    private val itemListener: ItemListener?
    get() = activity?.let { it as? ItemListener }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        view.let { it as ViewGroup }
            .children
            .mapNotNull { it as? Button }
            .forEach { button ->
                button.setOnClickListener {
                    onClick()
                }
            }
    }

    override fun onDetach() {
         super.onDetach()
    }

    private fun onClick() {
        itemListener?.onItemSeleted()
    }

}