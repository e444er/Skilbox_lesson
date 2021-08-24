package com.devv.dependencyinjection.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devv.dependencyinjection.helper.State
import com.devv.dependencyinjection.models.TvShowItem
import com.devv.dependencyinjection.repository.TvShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel
@Inject
constructor(private val repository: TvShowRepository) : ViewModel() {

//    private val _response = MutableLiveData<List<TvShowItem>>()
//    val response : LiveData<List<TvShowItem>>
//    get() =  _response

    private val _response = MutableStateFlow<State>(State.Loading)

    val response = _response.asStateFlow()

    init {
        getAllTvShows()
    }

    private fun getAllTvShows(){
        viewModelScope.launch {
            _response.value = State.Loading
            repository.getTvShows()
                .catch { _response.value = State.Error(it) }
                .collect { tv ->
                    _response.value = State.Success(tv)
                }
        }
    }

//    private fun getAllTvShows() {
//        viewModelScope.launch {
//            repository.getTvShows().let { response ->
//                if (response.isSuccessful){
//                    _response.postValue(response.body())
//                }else{
//                    Log.d("tag", "getAllTvShows Error: ${response.code()}")
//                }
//            }
//        }
//    }
}