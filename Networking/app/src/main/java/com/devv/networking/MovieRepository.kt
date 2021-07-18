package com.devv.networking

import android.util.Log
import com.devv.networking.Network.getSearchMovieCall
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject

class MovieRepository {

    fun searchMovie(
        text: String,
        text1: String,
        typec: String, callback: (List<RemateMovie>) -> Unit,
    ): Call {
        return getSearchMovieCall(text, text1, typec).apply {
            enqueue(object : Callback {
                override fun onFailure(call: Call, e: java.io.IOException) {
                    callback(emptyList())
                }

                override fun onResponse(call: Call, response: Response) {
                    val responseString = response.body?.string().orEmpty()
                    if (response.isSuccessful) {
                        try {
                            val jsonObject = JSONObject(responseString)
                            val movieString = jsonObject.getString("Search")
                            // если ничего не найдено бросим исключение
                            if (movieString.isEmpty())
                            // ошибка
                                error("No results were found for this request")
                            Log.d("TAG", movieString)
                            // парсит Moshi, получаем лист
                            val movies = parseRes(responseString)
                            callback(movies)
                        } catch (e: JSONException) {
                            error("Error parse response: ${e.message}")
                        }
                    } else {
                        var errorResponse = "Error response: ${response.code}-${response.message}"
                        try {
                            errorResponse += "\n" + JSONObject(responseString).getString("Error")
                        } catch (ignore: JSONException) {
                        }
                        error(errorResponse)
                    }
                }
            })
        }
    }

    private fun parseRes(responseBody: String): List<RemateMovie> {
        val moshi = Moshi.Builder().build()
        val typ = Types.newParameterizedType(
            List::class.java,
            RemateMovie::class.java
        )
        val adapter = moshi.adapter<List<RemateMovie>>(typ).nonNull()
        try {
            val mov = adapter.fromJson(responseBody) ?: emptyList()
            return mov
        } catch (e: JSONException) {
            return emptyList()
        }

//        return try {
//            val jsonObject = JSONObject(responseBody)
//            val movieArray = jsonObject.getJSONArray("Search")
//
//            (0 until movieArray.length()).map { index ->
//                movieArray.getJSONObject(index)
//            }.map { movieJsonObject ->
//                val title = movieJsonObject.getString("Title")
//                val year = movieJsonObject.getString("Year")
//                val id = movieJsonObject.getString("imdbID")
//                val type = movieJsonObject.getString("Type")
//                val poster = movieJsonObject.getString("Poster")
//
//                RemateMovie(id = id, title = title, year = year, type = type, poster = poster)
//            }
//        } catch (e: JSONException) {
//            return emptyList()
//        }
    }
}