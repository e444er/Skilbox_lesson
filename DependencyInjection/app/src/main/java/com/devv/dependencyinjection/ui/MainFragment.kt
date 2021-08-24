package com.devv.dependencyinjection.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.devv.dependencyinjection.R
import com.devv.dependencyinjection.adapter.TvAdapter
import com.devv.dependencyinjection.databinding.MainFragmentBinding
import com.devv.dependencyinjection.viewmodel.TvShowViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    private val binding: MainFragmentBinding by viewBinding(MainFragmentBinding::bind)
    private val viewmodel: TvShowViewModel by viewModels()
    private var tvAdapter: TvAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
    }

    private fun setupRv() {
        tvAdapter = TvAdapter()
        with(binding.recyclerview){
            adapter = tvAdapter
            layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
        with(binding.rvEpisode){
            adapter = tvAdapter
            layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
        with(binding.rvRecentlyAdded){
            adapter = tvAdapter
            layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
        viewmodel.response.observe(viewLifecycleOwner, { tvAdapter?.differ?.submitList(it)})
    }

    override fun onDestroy() {
        super.onDestroy()
        tvAdapter = null
    }
}