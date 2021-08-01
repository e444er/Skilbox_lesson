package com.devv.roomdao.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.devv.roomdao.db.UserContaract.TABLE_NAME


@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(user: User)

    @Query("SELECT * FROM ${TABLE_NAME} ORDER BY id ASC")
    fun getAllUsers(): LiveData<List<User>>

    @Transaction
    @Query("SELECT * FROM ${TABLE_NAME}")
    suspend fun getAllUserRelations():List<UserRelations>

//    @Query("SELECT * FROM ${TABLE_NAME} WHERE ${ID} = :userId")
//    suspend fun getUserById(userId: Long):User?

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun removeUser(user: User)

//    @Query("DELETE FROM ${TABLE_NAME} WHERE ${ID} = :userId")
//    suspend fun removeUserById(userId: Long)

}