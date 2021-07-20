package com.devv.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val repository = UserRepository()

    private val isLoadingLiveData = MutableLiveData<Boolean>(false)
    private val userListLiveData = MutableLiveData<List<ReamteUser>>()

    val userList: LiveData<List<ReamteUser>>
        get() = userListLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    fun search(query: String) {

        viewModelScope.launch {
            isLoadingLiveData.postValue(true)
            try {
                val users = repository.searchUsers(query)
                userListLiveData.postValue(users)
            } catch (T: Throwable) {
                userListLiveData.postValue(emptyList())
            } finally {
                isLoadingLiveData.postValue(false)
            }
        }
    }
}