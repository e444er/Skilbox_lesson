package com.devv.flow.homework

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepository {

    fun search(s: String): Flow<List<Search>> {
        return flow {
            emit(Network.api.getMovie(s).Search)
        }
            .catch { Log.d("TAGG", "Repository") }
            .flowOn(Dispatchers.IO)
    }
}