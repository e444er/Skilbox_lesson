package com.devv.flow.homework

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class MovieRepository(private val modelDao:ModelDao) {

    fun search(s: String): Flow<List<Search>> {
        return flow {
            emit(Network.api.getMovie(s).Search)
        }
            .onEach { modelDao.insertUsers(it) }
            .catch { modelDao.getAllUsers() }
            .flowOn(Dispatchers.IO)
    }
}