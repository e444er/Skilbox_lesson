package com.devv.roomdao.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.devv.roomdao.db.UserContaract.Columns.AVATAR
import com.devv.roomdao.db.UserContaract.Columns.EMAIL
import com.devv.roomdao.db.UserContaract.Columns.FIRST_NAME
import com.devv.roomdao.db.UserContaract.Columns.ID
import com.devv.roomdao.db.UserContaract.Columns.LAST_NAME
import com.devv.roomdao.db.UserContaract.TABLE_NAME
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = TABLE_NAME,
//    indices = [Index(LAST_NAME)
)
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Long,
    @ColumnInfo(name = FIRST_NAME)
    val firstName: String,
    @ColumnInfo(name = LAST_NAME)
    val lastName: String,
    @ColumnInfo(name = EMAIL)
    val emailName: String,
    @ColumnInfo(name = AVATAR)
    val avatar: String?,
    @ColumnInfo(name = "age",defaultValue = "0" )
    val age: Int
) : Parcelable