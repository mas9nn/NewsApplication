package com.example.newsapp.data.local

import androidx.room.TypeConverter
import com.example.newsapp.domain.model.Source
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {
    @TypeConverter
    fun fromSource(source: Source?): String? {
        return Gson().toJson(source)
    }

    @TypeConverter
    fun toSource(source: String?): Source? {
        val sourceType = object : TypeToken<Source>() {}.type
        return Gson().fromJson(source, sourceType)
    }
}