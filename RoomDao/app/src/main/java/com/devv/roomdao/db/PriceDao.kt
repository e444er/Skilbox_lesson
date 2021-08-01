package com.devv.roomdao.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface PriceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrice(price: List<Price>)
}