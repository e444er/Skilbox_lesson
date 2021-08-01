package com.devv.roomdao.list

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.devv.roomdao.UserRepository
import com.devv.roomdao.db.User
import com.devv.roomdao.db.UserDatabase
import kotlinx.coroutines.launch

class UserViewModel(app: Application) : AndroidViewModel(app) {
    val readAllData: LiveData<List<User>>
    private val repo: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(app).userDao()
        repo = UserRepository(userDao)
        readAllData = repo.getAllUsers
    }

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