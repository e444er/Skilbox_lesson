package com.devv.flow.homework

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Search::class], version = 1)
abstract class ModelDatabase : RoomDatabase() {
    abstract fun modelDao(): ModelDao

    companion object {
        @Volatile
        private var INSTANCE: ModelDatabase? = null

        fun getDatabase(context: Context): ModelDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ModelDatabase::class.java,
                    "model_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}