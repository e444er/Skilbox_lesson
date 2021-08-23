package com.devv.roomdao.di

import android.app.Application
import androidx.room.Room
import com.devv.roomdao.db.Dao
import com.devv.roomdao.db.MIGRATION_1_2
import com.devv.roomdao.db.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModul {

    @Provides
    @Singleton
    fun providesDatabase(application: Application): UserDatabase {
        return  Room.databaseBuilder(
            application.applicationContext,
            UserDatabase::class.java,
            "user_database"
        ).addMigrations(MIGRATION_1_2)
            .build()
    }

    @Provides
    fun providesDao(db:UserDatabase): Dao {
        return db.userDao()
    }

}