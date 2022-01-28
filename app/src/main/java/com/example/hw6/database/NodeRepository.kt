package com.example.hw6.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class NodeRepository(private val wordDao: NodeDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allNodes: Flow<List<NodeEntity>> = wordDao.getAll()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(nodeEntity: NodeEntity) {
        wordDao.insert(nodeEntity)
    }

//    @Suppress("RedundantSuspendModifier")
//    @WorkerThread
//    suspend fun loadAll(): Flow<List<NodeEntity>> {
//        return wordDao.getAll()
//    }
}