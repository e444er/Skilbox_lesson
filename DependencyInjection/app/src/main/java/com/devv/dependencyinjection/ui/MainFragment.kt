package com.devv.dependencyinjection.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.devv.dependencyinjection.R
import com.devv.dependencyinjection.adapter.TvAdapter
import com.devv.dependencyinjection.databinding.MainFragmentBinding
import com.devv.dependencyinjection.helper.State
import com.devv.dependencyinjection.viewmodel.TvShowViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    private val binding: MainFragmentBinding by viewBinding(MainFragmentBinding::bind)
    private val viewmodel: TvShowViewModel by viewModels()
    private var tvAdapter: TvAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        bindModel()
    }

    private fun bindModel() {
        lifecycleScope.launchWhenStarted {
            viewmodel.response.collect { uiState ->
                when(uiState) {
                    is State.Loading -> {
                        binding.progressBar.isVisible = true
                        binding.recyclerview.isVisible = false
                        binding.rvEpisode.isVisible = false
                        binding.rvRecentlyAdded.isVisible = false
                    }
                    is State.Success -> {
                        binding.progressBar.isVisible = false
                        binding.recyclerview.isVisible = true
                        binding.rvEpisode.isVisible = true
                        binding.rvRecentlyAdded.isVisible = true
                        tvAdapter?.differ?.submitList(uiState.tvItem)
                    }
                    is State.Error -> {
                        binding.progressBar.isVisible = false
                        binding.recyclerview.isVisible = false
                        binding.rvEpisode.isVisible = false
                        binding.rvRecentlyAdded.isVisible = false
                        Toast.makeText(requireContext(), "${uiState.msg}", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
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
//        viewmodel.response.observe(viewLifecycleOwner, { tvAdapter?.differ?.submitList(it)})
    }

    override fun onDestroy() {
        super.onDestroy()
        tvAdapter = null
    }
}