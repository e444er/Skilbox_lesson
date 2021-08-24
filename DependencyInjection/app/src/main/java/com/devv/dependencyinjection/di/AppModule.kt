package com.devv.dependencyinjection.di

import com.devv.dependencyinjection.api.ApiService
import com.devv.dependencyinjection.helper.Contants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providesBaseUrl() = Contants.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofitInstance(BASE_URL:String) :ApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
}