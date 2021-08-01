package com.devv.roomdao.db

import androidx.room.Embedded
import androidx.room.Relation
import com.devv.roomdao.db.UserContaract.Columns.ID

data class UserRelations(
    @Embedded
    val user: User,
    @Relation(
        parentColumn = ID,
        entityColumn = "user_id"
    )
    val price: List<Price>,
)