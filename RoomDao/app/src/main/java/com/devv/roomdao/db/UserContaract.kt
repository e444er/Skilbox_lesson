package com.devv.roomdao.db

import androidx.room.ColumnInfo

object UserContaract {
    const val TABLE_NAME = "users"

    object Columns{
        const val ID = "id"
        const val FIRST_NAME = "firstName"
        const val LAST_NAME = "lastName"
        const val EMAIL = "emailName"
        const val AVATAR = "avatar"
    }
}