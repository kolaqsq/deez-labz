package com.example.hw6.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class NodeRepository(private val nodeDao: NodeDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allNodes: Flow<List<NodeEntity>> = nodeDao.getAll()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(nodeEntity: NodeEntity) {
        nodeDao.insert(nodeEntity)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(nodeEntity: NodeEntity) {
        nodeDao.update(nodeEntity)
    }
}