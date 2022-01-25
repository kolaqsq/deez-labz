package com.example.hw6.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hw6.database.dao.NodeDao
import com.example.hw6.database.entity.NodeEntity

@Database(entities = [NodeEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun nodeDao(): NodeDao
}