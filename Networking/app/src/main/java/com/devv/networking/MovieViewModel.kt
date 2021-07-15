package com.devv.networking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.Call

class MovieViewModel : ViewModel() {

    private val repository = MovieRepository()

    private var currentCall: Call? = null

    private val movieListLiveData = MutableLiveData<List<RemateMovie>>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()

    val moveList: LiveData<List<RemateMovie>>
        get() = movieListLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    fun search(text: String) {
        isLoadingLiveData.postValue(true)
        currentCall = repository.searchMovie(text) { movies ->
            isLoadingLiveData.postValue(false)
            movieListLiveData.postValue(movies)
            currentCall = null
        }
    }

    override fun onCleared() {
        super.onCleared()
        currentCall?.cancel()
    }
}