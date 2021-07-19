package com.devv.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class AddHeader(private var token:String):Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
        val modifiedRequest = originRequest.newBuilder()
            .addHeader(TOKEN,token)
            .build()

        val response = chain.proceed(modifiedRequest)
        return response
    }
}