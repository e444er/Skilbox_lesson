package com.devv.roomdao.di

import com.devv.roomdao.UserRepository
import com.devv.roomdao.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesRepo(impl: UserRepositoryImpl): UserRepository
}