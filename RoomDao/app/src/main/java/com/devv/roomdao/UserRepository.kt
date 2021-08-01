package com.devv.roomdao

import androidx.lifecycle.LiveData
import com.devv.roomdao.db.Dao
import com.devv.roomdao.db.User

class UserRepository(private val userDao: Dao) {

    val getAllUsers: LiveData<List<User>> = userDao.getAllUsers()

    suspend fun saveUser(user: User) = userDao.insertUsers(user)

    suspend fun updateUser(user: User) = userDao.updateUser(user)

    suspend fun removeUser(user: User) = userDao.removeUser(user)


}