package com.devv.retrofit

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Detail(
    val login: String,
    val id: Long,
    val avatar_url: String?,
    val name: String,
    val following: Int,
    val followers: Int,
)