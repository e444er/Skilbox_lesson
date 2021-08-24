package com.devv.dependencyinjection.api

import com.devv.dependencyinjection.helper.Contants
import com.devv.dependencyinjection.models.TvShowResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Contants.END_POINT)
    suspend fun getTvShows():Response<TvShowResponse>
}