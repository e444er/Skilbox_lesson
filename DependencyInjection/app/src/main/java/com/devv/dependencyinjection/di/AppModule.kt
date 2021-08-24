package com.devv.dependencyinjection.di

import com.devv.dependencyinjection.api.ApiService
import com.devv.dependencyinjection.helper.Contants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    fun okHttpClient(): OkHttpClient {
        val levelType: HttpLoggingInterceptor.Level =
            HttpLoggingInterceptor.Level.BODY

        val logging = HttpLoggingInterceptor()
        logging.setLevel(levelType)

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    fun providesBaseUrl() = Contants.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofitInstance(BASE_URL:String,okHttpClient: OkHttpClient) :ApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
}