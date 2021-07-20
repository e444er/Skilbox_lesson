package com.devv.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val repository = DetailRepository()
    private val userLiveData = MutableLiveData<List<Detail>>()

    val getdetailList: LiveData<List<Detail>>
        get() = userLiveData

    fun setUserDetail(username: String) {
        viewModelScope.launch {
            try {
                val users = repository.search(username)
                userLiveData.postValue(users)
            } catch (T: Throwable) {
                userLiveData.postValue(emptyList())
            }
        }
    }
}



