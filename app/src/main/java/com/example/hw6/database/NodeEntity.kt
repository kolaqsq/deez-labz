package com.example.hw6.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "node")
data class NodeEntity(
    @PrimaryKey(autoGenerate = true) @NonNull val id: Int,
    @ColumnInfo(name = "value") val value: Int,
    // Nodes is a list of parents of this node
    @TypeConverters(NodeConverter::class)
    @ColumnInfo(name = "nodes") var nodes: List<NodeEntity>
)
