package com.devv.networking

import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

object Network {
    val client = OkHttpClient()

    fun getSearchMovieCall(text: String): Call{
        val url = HttpUrl.Builder()
            .scheme("http")
            .host("www.omdbapi.com")
            .addQueryParameter("apikey", KEY)
            .addQueryParameter("s", text)
            .build()

        val request = Request.Builder()
            .get()
            .url(url)
            .build()

        return client.newCall(request)
    }
}