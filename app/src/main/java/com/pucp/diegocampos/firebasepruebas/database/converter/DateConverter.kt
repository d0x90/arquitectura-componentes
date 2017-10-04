package com.pucp.diegocampos.firebasepruebas.database.converter

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * Created by desarrollo on 04/10/2017.
 */
class DateConverter {

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}