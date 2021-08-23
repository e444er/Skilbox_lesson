package com.devv.roomdao.di

import com.devv.roomdao.UserRepository
import com.devv.roomdao.UserRepositoryImpl
import com.devv.roomdao.db.Databasee
import com.devv.roomdao.list.UserViewModel

object DiCon {

    private fun getUserRepository(): UserRepository {
        return UserRepositoryImpl(Databasee.instance.userDao())
    }

    fun getUserlist(): UserViewModel {
        val model = Databasee.instance.userDao()
        val repo = UserRepositoryImpl(model)
        return UserViewModel(repo)
    }
}