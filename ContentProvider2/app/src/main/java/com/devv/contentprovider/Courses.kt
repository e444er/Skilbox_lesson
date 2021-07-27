package com.devv.contentprovider

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Courses(
    val id: Long,
    val title: String,
)