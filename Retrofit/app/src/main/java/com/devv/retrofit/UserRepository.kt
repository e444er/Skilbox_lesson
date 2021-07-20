package com.devv.retrofit

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository {

    suspend fun searchUsers(
        query: String,
    ): List<ReamteUser> {
        return withContext(Dispatchers.IO) {
            Network.api.searchUsers(query).items
        }
    }
}