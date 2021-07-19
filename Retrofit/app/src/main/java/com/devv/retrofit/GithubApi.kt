package com.devv.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("search/users")
    fun searchUsers(
        @Query("q") query: String,
    ): Call<ServerItems<ReamteUser>>
//            Response<ReamteUser>

    @GET("users/{username}")
    fun getUserDetail(
        @Path("username") username: String,
    ): Call<Detail>

    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String,
    ): Call<ArrayList<ReamteUser>>

    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String,
    ): Call<ArrayList<ReamteUser>>
}