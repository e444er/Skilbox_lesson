package com.devv.roomdao.db

import androidx.room.TypeConverter
import com.devv.roomdao.PriceStatus

class PriceStatusConverter {

    @TypeConverter
    fun converterStatusToString(status: PriceStatus): String = status.name

    @TypeConverter
    fun converterToStringStatus(statusString: String): PriceStatus =
        PriceStatus.valueOf(statusString)
}