package com.testapp.challenge.model.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.testapp.challenge.model.network.dto.Point
import java.lang.reflect.Type


/**
 * @author aliakseicherniakovich
 */
class PointDbConverter {

    @TypeConverter
    fun convertPointListToGson(source: List<Point>): String = Gson().toJson(source)

    @TypeConverter
    fun convertGsonToPointList(source: String): List<Point> {
        val listType: Type = object : TypeToken<List<Point>>() {}.type
        return Gson().fromJson(source, listType)
    }
}
