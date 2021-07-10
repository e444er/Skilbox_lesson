package com.devv.fragments

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.devv.fragments.databinding.FragmentListBinding


class ListFragment : Fragment(R.layout.fragment_list) {

    private lateinit var binding: FragmentListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        view.let { it as ViewGroup }
            .children
            .mapNotNull { it as? TextView }
            .forEach { TextView ->
                TextView.setOnClickListener {
                    parentFragmentManager.beginTransaction()
                        .replace(
                            R.id.listFragment, DetailFragment.newInstance(
                                (resources.getString(
                                    when (TextView.text.toString()) {
                                        "What Tree is" -> R.string.w_t_i
                                        "Number of trees" -> R.string.w_t_i
                                        "Roots of tree" -> R.string.w_t_i
                                        "Trunk of tree" -> R.string.w_t_i
                                        else -> R.string.w_t_i
                                    }
                                ))
                            )
                        )
                        .addToBackStack(null)
                        .commit()
                }
            }
    }
}