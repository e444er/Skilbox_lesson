package com.devv.roomdao.db

import androidx.room.*
import com.devv.roomdao.PriceStatus
import com.devv.roomdao.db.UserContaract.Columns.ID

@Entity(tableName = "price",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = [ID],
        childColumns = ["user_id"]
    )])
@TypeConverters(PriceStatusConverter::class)
data class Price(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "user_id")
    val userId: Long,
    @ColumnInfo(name = "price")
    val price: Int,
    @ColumnInfo(name = "status")
    val status: PriceStatus,
)