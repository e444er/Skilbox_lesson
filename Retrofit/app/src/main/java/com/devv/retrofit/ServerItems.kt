package com.devv.retrofit

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ServerItems<T>(
    val items: List<T>
)