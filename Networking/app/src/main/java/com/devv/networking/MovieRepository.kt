package com.devv.networking

import com.devv.networking.Network.getSearchMovieCall
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject

class MovieRepository {

    fun searchMovie(text: String, callback: (List<RemateMovie>) -> Unit): Call {
        return getSearchMovieCall(text).apply {
            enqueue(object : Callback {
                override fun onFailure(call: Call, e: java.io.IOException) {
                    callback(emptyList())
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val responseString = response.body?.string().orEmpty()
                        val movies = parseRes(responseString)
                        callback(movies)
                    } else {
                        callback(emptyList())
                    }
                }
            })
        }
    }

//        Thread {
//            try {
//                val response = getSearchMovieCall(text).execute()
//                val responseString = response.body?.string().orEmpty()
//                val movies = parseRes(responseString)
//                callback(movies)
//            } catch (e: IOException) {
//                callback(emptyList())
//            }
//        }.start()


    private fun parseRes(responseBody: String): List<RemateMovie> {
        return try {
            val jsonObject = JSONObject(responseBody)
            val movieArray = jsonObject.getJSONArray("Search")

            (0 until movieArray.length()).map { index ->
                movieArray.getJSONObject(index)
            }.map { movieJsonObject ->
                val title = movieJsonObject.getString("Title")
                val year = movieJsonObject.getString("Year")
                val id = movieJsonObject.getString("imdbID")

                RemateMovie(id = id, title = title, year = year)
            }
        } catch (e: JSONException) {
            return emptyList()
        }
    }
}