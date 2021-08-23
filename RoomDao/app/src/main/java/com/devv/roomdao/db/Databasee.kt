package com.devv.roomdao.db

import android.content.Context
import androidx.room.Room

object Databasee {
    lateinit var instance: UserDatabase
        private set

    fun initD(context: Context) {
        instance = Room.databaseBuilder(
            context.applicationContext,
            UserDatabase::class.java,
            "user_database"
        ).addMigrations(MIGRATION_1_2)
            .build()
    }
}