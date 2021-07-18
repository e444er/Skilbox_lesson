package com.devv.networking

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemateMovie(
    @Json(name = "imdbID")
    val id: String,
    val title: String,
    val year: String,
    val type: String,
    val poster: String
)