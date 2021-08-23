package com.devv.roomdao

import androidx.lifecycle.LiveData
import com.devv.roomdao.db.Dao
import com.devv.roomdao.db.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: Dao
    ) :UserRepository{

    override val getAllUsers: LiveData<List<User>> = userDao.getAllUsers()

    override suspend fun saveUser(user: User) = userDao.insertUsers(user)

    override suspend fun updateUser(user: User) = userDao.updateUser(user)

    override suspend fun removeUser(user: User) = userDao.removeUser(user)


}