package com.example.hw6.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@TypeConverters(NodeConverter::class)
@Database(entities = [NodeEntity::class], version = 4, exportSchema = false)
abstract class NodeDatabase : RoomDatabase() {
    abstract fun nodeDao(): NodeDao

    private class NodeDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.nodeDao())
                }
            }
        }

        suspend fun populateDatabase(nodeDao: NodeDao) {
            // Delete all content here.
            nodeDao.deleteAll()

            // Add sample nodes.
            var node = NodeEntity(0, 1, listOf())
            nodeDao.insert(node)
            node = NodeEntity(0, 2, listOf())
            nodeDao.insert(node)
            node = NodeEntity(0, 3, listOf())
            nodeDao.insert(node)
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NodeDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): NodeDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NodeDatabase::class.java,
                    "nodes-db"
                )
//                    .fallbackToDestructiveMigration()
                    .addCallback(NodeDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}