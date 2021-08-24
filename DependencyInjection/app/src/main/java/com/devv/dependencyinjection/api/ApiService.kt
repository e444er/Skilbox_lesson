package com.devv.dependencyinjection.api

import com.devv.dependencyinjection.helper.Contants
import com.devv.dependencyinjection.models.TvShowItem
import com.devv.dependencyinjection.models.TvShowResponse
import retrofit2.http.GET

interface ApiService {

    @GET(Contants.END_POINT)
    suspend fun getTvShows():TvShowResponse<TvShowItem>
}