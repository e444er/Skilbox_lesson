package com.devv.dependencyinjection.repository

import com.devv.dependencyinjection.api.ApiService
import com.devv.dependencyinjection.models.TvShowItem
import com.devv.dependencyinjection.models.TvShowResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class TvShowRepository
@Inject constructor(private val apiService: ApiService){
//    suspend fun getTvShows() = apiService.getTvShows()

    fun getTvShows(): Flow<List<TvShowItem>> {
        return flow {
            emit(apiService.getTvShows())
        }.flowOn(Dispatchers.IO)
    }
}