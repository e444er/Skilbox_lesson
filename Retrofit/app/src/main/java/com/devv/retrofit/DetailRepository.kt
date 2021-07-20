package com.devv.retrofit

class DetailRepository {

    suspend fun search(username: String): List<Detail> {
        return Network.api.getUserDetail(username).items
    }
}