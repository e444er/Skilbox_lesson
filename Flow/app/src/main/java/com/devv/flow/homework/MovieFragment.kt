package com.devv.flow.homework

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
import com.devv.flow.R
import com.devv.flow.databinding.MovieFragmentBinding
import com.devv.flow.lesson.exfun.textChangeFlow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieFragment : Fragment(R.layout.movie_fragment) {

    private val binding: MovieFragmentBinding by viewBinding(MovieFragmentBinding::bind)
    private var movieAdapter: MoviewAdapter? = null
    private val viewModel: MovieViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        bindModel()
        viewLifecycleOwner.lifecycleScope.launch {
            binding.editTextMovie.textChangeFlow()
                .debounce(1000)
                .distinctUntilChanged()
                .catch { Log.d("MOVIE", "Error is : ${it}") }
                .collect { viewModel.search(it) }
        }
    }

    private fun bindModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.movieList.collect { uiState ->
                when (uiState) {
                    is Resull.Loading -> {
                        binding.movieList.isVisible = false
                        binding.progressBar2.isVisible = true
                    }
                    is Resull.Success -> {
                        binding.movieList.isVisible = true
                        binding.progressBar2.isVisible = false
                        movieAdapter?.differ?.submitList(uiState.search)
                    }
                    is Resull.Error -> {
                        binding.movieList.isVisible = false
                        binding.progressBar2.isVisible = false
                        Toast.makeText(requireContext(), "${uiState.msg}", Toast.LENGTH_SHORT)
                            .show()
                        Log.d("MOVIE", "Error is 2 : ${uiState.msg}")
                    }
                }
            }
        }
    }

    private fun initList() {
        movieAdapter = MoviewAdapter()
        with(binding.movieList) {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        movieAdapter = null
    }
}