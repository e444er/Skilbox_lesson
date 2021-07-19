package com.devv.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    val user = MutableLiveData<Detail>()

    fun setUserDetail(username: String) {
        Network.api.getUserDetail(username)
            .enqueue(object : Callback<Detail> {
                override fun onResponse(call: Call<Detail>, response: Response<Detail>) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<Detail>, t: Throwable) {
                    error("error")
                }
            })
    }

    fun getUserDetail(): LiveData<Detail> {
        return user
    }
}