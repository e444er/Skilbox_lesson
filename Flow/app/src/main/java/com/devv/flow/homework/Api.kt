package com.devv.flow.homework

import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("?apiKey=24dabaf8")
    suspend fun getMovie(
        @Query(value = "s") searchTitle: String
    ): Result<Search>
}