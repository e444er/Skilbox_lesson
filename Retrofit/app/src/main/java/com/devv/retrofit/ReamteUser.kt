package com.devv.retrofit

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReamteUser(
    @Json(name = "login")
    val username: String,
    @Json(name = "avatar_url")
    val avatar: String?,
    val id: Long,
)