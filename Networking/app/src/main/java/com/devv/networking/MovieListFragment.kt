package com.devv.networking

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.devv.networking.databinding.FragmentMovieListBinding

class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private lateinit var binding: FragmentMovieListBinding
    private var movieAdapter: MovieAdapter? = null
    private val viewModel: MovieViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieListBinding.bind(view)
        initList()
        bindViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        movieAdapter = null
    }

    private fun initList() {
        movieAdapter = MovieAdapter()
        with(binding.recyclerview) {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun bindViewModel() {
        binding.button.setOnClickListener {
            val queryText = binding.editText.text.toString()
            viewModel.search(queryText)
        }

        viewModel.isLoading.observe(viewLifecycleOwner, ::updateState)
        viewModel.moveList.observe(viewLifecycleOwner) {
            movieAdapter?.items = it
        }
    }

    private fun updateState(isLoading: Boolean) {
        binding.recyclerview.isVisible = isLoading.not()
        binding.progressBar.isVisible = isLoading
        binding.button.isEnabled = isLoading.not()
    }

}