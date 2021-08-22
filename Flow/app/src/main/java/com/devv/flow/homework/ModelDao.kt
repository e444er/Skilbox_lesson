package com.devv.flow.homework

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(model: List<Search>)

    @Query("SELECT * FROM `model.db`")
    fun getAllUsers(): Flow<List<Search>>
}