package com.devv.scopedstoragee.test

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create

object Network {

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY))
        .followRedirects(true)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://google.com")
        .client(client)
        .build()

    val api: Api
    get() = retrofit.create()
}