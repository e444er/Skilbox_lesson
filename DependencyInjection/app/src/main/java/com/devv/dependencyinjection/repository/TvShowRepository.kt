package com.devv.dependencyinjection.repository

import com.devv.dependencyinjection.api.ApiService
import javax.inject.Inject

class TvShowRepository
@Inject constructor(private val apiService: ApiService){
    suspend fun getTvShows() = apiService.getTvShows()
}