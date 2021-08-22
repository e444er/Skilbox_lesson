package com.devv.flow.homework

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieViewModel(private val repo : MovieRepository) : ViewModel() {

    private val _movieList = MutableStateFlow<Resull?>(Resull.Empty)

    val movieList: StateFlow<Resull?>
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

    //    fun search(s: String) {
//            repo.search(s)
//                .onStart { _movieList.value = Resull.Loading  }
//                .onEach {  fav -> _movieList.value = Resull.Success(fav) }
//                .catch { _movieList.value = Resull.Error(it) }
//                .launchIn(viewModelScope)
//        }
//    }
}