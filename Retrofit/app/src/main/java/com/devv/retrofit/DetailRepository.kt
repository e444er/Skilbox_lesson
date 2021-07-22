package com.devv.retrofit

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailRepository {

    suspend fun search(username: String): Detail {
        return withContext(Dispatchers.IO) {
            Network.api.getUserDetail(username)
        }
    }
}