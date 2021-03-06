package com.devv.broadcastreceiver.firebase

import com.devv.broadcastreceiver.firebase.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstancs {

    companion object {
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val api by lazy {
            retrofit.create(NotiAPI::class.java)
        }
    }
}