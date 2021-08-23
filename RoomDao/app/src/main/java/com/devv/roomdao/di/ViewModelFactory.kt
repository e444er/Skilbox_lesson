package com.devv.roomdao.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devv.roomdao.list.UserViewModel

class ViewModelFactory:ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when(modelClass){
            UserViewModel::class.java -> DiCon.getUserlist() as T
            else -> error("ViewModelFactory")
        }
    }
}