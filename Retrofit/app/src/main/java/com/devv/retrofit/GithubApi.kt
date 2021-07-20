package com.devv.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String,
    ): ServerItems<ReamteUser>
//            Response<ReamteUser>

    @GET("users/{username}")
    suspend fun getUserDetail(
        @Path("username") username: String,
    ): ServerItems<Detail>

    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String,
    ): Call<ArrayList<ReamteUser>>

    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String,
    ): Call<ArrayList<ReamteUser>>
}