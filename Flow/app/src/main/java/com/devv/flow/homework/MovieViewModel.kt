package com.devv.flow.homework

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val repo = MovieRepository()

    private val _movieList = MutableStateFlow<Resull>(Resull.Empty)

    val movieList: StateFlow<Resull>
        get() = _movieList

    fun search(s: String) {
        viewModelScope.launch {
            _movieList.value = Resull.Loading
            repo.search(s)
                .catch { _movieList.value = Resull.Error(it) }
                .collect { fav ->
                    _movieList.value = Resull.Success(fav)
                }
        }
    }
}