package com.example.hw6.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NodeDao {
    @Query("SELECT * FROM node ORDER BY id ASC")
    fun getAll(): Flow<List<NodeEntity>>

    @Query("SELECT * FROM node WHERE id = (:nodeId)")
    fun get(nodeId: Int): NodeEntity

    @Insert
    suspend fun insert(nodeEntity: NodeEntity)

    @Delete
    suspend fun delete(nodeEntity: NodeEntity)

    @Query("DELETE FROM node")
    suspend fun deleteAll()
}