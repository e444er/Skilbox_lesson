package com.devv.retrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

//    fun searchUsers(
//        query: String,
//        onComplete: (List<ReamteUser>) -> Unit,
//        onError: (Throwable) -> Unit,
//    ) {
//        Network.api.searchUsers(query).enqueue(
//            object : Callback<ServerItems<ReamteUser>> {
//                override fun onResponse(
//                    call: Call<ServerItems<ReamteUser>>,
//                    response: Response<ServerItems<ReamteUser>>,
//                ) {
//                    if (response.isSuccessful) {
//                        onComplete(response.body()?.items.orEmpty())
//                    } else {
//                        onError(RuntimeException("RunTimeError"))
//                    }
//                }
//
//                override fun onFailure(call: Call<ServerItems<ReamteUser>>, t: Throwable) {
//                    onError(t)
//                }
//
//            }
//        )
//    }
}