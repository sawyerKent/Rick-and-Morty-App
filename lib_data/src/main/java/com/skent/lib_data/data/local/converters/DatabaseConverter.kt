package com.skent.lib_data.data.local.converters

import androidx.room.TypeConverter
import com.skent.lib_data.domain.models.character.Location
import com.skent.lib_data.domain.models.character.Origin

class DatabaseConverter {
    private val separator = ","

    @TypeConverter
    fun convertListToString(list: List<String>): String {
        val stringBuilder = StringBuilder()
        if(list.isNotEmpty()){
            for (item in list) {
                stringBuilder.append(item).append(separator)
            }
            stringBuilder.setLength(stringBuilder.length - separator.length)
            return stringBuilder.toString()
        }
        return ""
    }

    @TypeConverter
    fun convertStringToList(string: String): List<String> {
        return string.split(separator)
    }

    @TypeConverter
    fun locationToString(location: Location): String{
        return location.toString()
    }

    @TypeConverter
    fun stringToLocation(name: String): Location {
        return Location(name, name)
    }

    @TypeConverter
    fun originNameToString(origin: Origin): String{
        return origin.toString()
    }

    @TypeConverter
    fun stringNameToOrigin(name: String): Origin {
        return Origin(name, name)
    }
}
