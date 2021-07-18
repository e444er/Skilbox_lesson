package com.devv.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    private val repository = UserRepository()

    private val isLoadingLiveData = MutableLiveData<Boolean>(false)
    private val userListLiveData = MutableLiveData<List<ReamteUser>>()

    val userList: LiveData<List<ReamteUser>>
        get() = userListLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    fun search(query: String) {
        isLoadingLiveData.postValue(true)
        repository.searchUsers(
            query = query,
            onComplete = { users ->
                isLoadingLiveData.postValue(false)
                userListLiveData.postValue(users)
            },
            onError = { throwable ->
                isLoadingLiveData.postValue(false)
                userListLiveData.postValue(emptyList())
            }
        )
    }
}