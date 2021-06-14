package com.devv.a01_generics

import kotlin.random.Random

sealed class Result<T, R> {
    data class Success<T, R>(val success: Int) : Result<T, R>()
    data class Error<T, R>(val error: String) : Result<T, R>()

    fun returnObject(): Result<Int, String> {
        if (Random.nextBoolean()) Success<T,R>(1)
        else Error<T,R>("Error")
        return returnObject()
    }

}

