package com.example.hw6.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.hw6.database.entity.NodeEntity

@Dao
interface NodeDao {
    @Query("SELECT * FROM node")
    fun getAll(): List<NodeEntity>

    @Query("SELECT * FROM node WHERE id IN (:nodeIds)")
    fun loadAllByIds(nodeIds: IntArray): List<NodeEntity>

    @Insert
    fun insertAll(vararg nodeEntities: NodeEntity)

    @Delete
    fun delete(nodeEntity: NodeEntity)
}