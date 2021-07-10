package com.devv.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.devv.fragments.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        binding.detailsTv.text = requireArguments().getString(KEY_TEXT)
    }

    companion object {
        private const val KEY_TEXT = "key_text"

        fun newInstance(text: String): DetailFragment {
            return DetailFragment().withArguments {
                putString(KEY_TEXT, text)
            }
        }
    }
}