package com.devv.roomdao

import androidx.lifecycle.LiveData
import com.devv.roomdao.db.User

interface UserRepository {
    val getAllUsers: LiveData<List<User>>

    suspend fun saveUser(user: User)

    suspend fun updateUser(user: User)

    suspend fun removeUser(user: User)

}