package com.devv.networking

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.devv.networking.databinding.FragmentMovieListBinding

class MovieListFragment : Fragment(R.layout.fragment_movie_list), AdapterView.OnItemClickListener {

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
        val type = resources.getStringArray(R.array.Type)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.list, type)
        binding.autoCom.setAdapter(arrayAdapter)

        with(binding.autoCom) {
            setAdapter(arrayAdapter)
            onItemClickListener = this@MovieListFragment
        }
        binding.button.setOnClickListener {
            val queryText = binding.editText.text.toString()
            val queryText1 = binding.editText2.text.toString()
            val typec = binding.autoCom.text.toString()
            viewModel.search(queryText, queryText1, typec)
        }

        viewModel.isLoading.observe(viewLifecycleOwner, ::updateState)
        viewModel.moveList.observe(viewLifecycleOwner) {
            movieAdapter?.items = it
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        parent?.getItemAtPosition(position).toString()
    }

    private fun updateState(isLoading: Boolean) {
        binding.recyclerview.isVisible = isLoading.not()
        binding.progressBar.isVisible = isLoading
        binding.button.isEnabled = isLoading.not()
    }
}