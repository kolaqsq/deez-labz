package com.example.hw6.database

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken

class NodeConverter {

    @TypeConverter
    fun fromNodes(nodes: List<NodeEntity>): String{
        return Gson().toJson(nodes)
    }

    @TypeConverter
    fun toNodes(nodes: String): List<NodeEntity>{
        val listType = object : TypeToken<List<NodeEntity>>() {}.type
        return Gson().fromJson(nodes, listType)
    }

}