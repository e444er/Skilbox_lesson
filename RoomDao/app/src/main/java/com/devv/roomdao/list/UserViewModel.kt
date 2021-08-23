package com.devv.roomdao.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devv.roomdao.UserRepository
import com.devv.roomdao.db.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repo: UserRepository) : ViewModel() {

    val readAllData: LiveData<List<User>> = repo.getAllUsers

    fun saveUsers(user: User) {
        try {
            viewModelScope.launch {
                repo.saveUser(user)
            }
        } catch (t: Throwable) {
            Log.d("ERRORR", "${saveUsers(user)}")
        }
    }

    fun deleteUsers(user: User) {
        try {
            viewModelScope.launch {
                repo.removeUser(user)
            }
        } catch (t: Throwable) {
            Log.d("ERRORR", "${saveUsers(user)}")
        }
    }

    fun updateUsers(user: User) {
        try {
            viewModelScope.launch {
                repo.updateUser(user)
            }
        } catch (t: Throwable) {
            Log.d("ERRORR", "${saveUsers(user)}")
        }
    }

}