package com.devv.networking

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

object Network {

    val flipperNetworkPlugin = NetworkFlipperPlugin()

    val client = OkHttpClient.Builder()
        .addNetworkInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .addNetworkInterceptor(FlipperOkhttpInterceptor(flipperNetworkPlugin))
        .build()
//    "Search": [
//    {
//        "Title": "A la conquête de Titan",
//        "Year": "2017",
//        "imdbID": "tt7424516",
//        "Type": "movie",
//        "Poster": "https://m.media-amazon.com/images/M/MV5BMmEyMmVkMGUtODMzYi00NWM3LWIyYzctNjY1NDcyZTZjZTg1XkEyXkFqcGdeQXVyNTM3MDMyMDQ@._V1_SX300.jpg"
//    },
//    https://omdbapi.com/?apikey=28731be1&s=titan&y=2017&type=movie

    fun getSearchMovieCall(text: String, text1: String, typec: String): Call {
        val url = HttpUrl.Builder()
            .scheme("http")
            .host("www.omdbapi.com")
            .addQueryParameter("apikey", KEY)
            .addQueryParameter("s", text)
            .addQueryParameter("y", text1)
            .addQueryParameter("type", typec.toString())
            .build()

        val request = Request.Builder()
            .get()
            .url(url)
            .build()

        return client.newCall(request)
    }
}