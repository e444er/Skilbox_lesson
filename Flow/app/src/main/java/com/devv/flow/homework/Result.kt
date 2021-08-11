package com.devv.flow.homework

data class Result<T>(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)