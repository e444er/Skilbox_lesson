package com.devv.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    val repo = DetailRepository()

    val user = MutableLiveData<Detail>()

    val getUserDetail: LiveData<Detail>
        get() = user

    fun setUserDetail(username: String) {
        viewModelScope.launch {
            try {
                val users = repo.search(username)
                user.postValue(users)
            } catch (T: Throwable) {
                user.postValue(null)
            }
        }
    }
}



