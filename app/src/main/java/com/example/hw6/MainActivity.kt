package com.example.hw6

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.hw6.database.AppDatabase
import com.example.hw6.database.entity.NodeEntity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startDatabase()
    }

    private fun startDatabase() {
        val db = Room.databaseBuilder(
            this,
            AppDatabase::class.java, "nodes-db"
        )
            .allowMainThreadQueries()
            .build()

        val nodeDao = db.nodeDao()

        val node = nodeDao.loadAllByIds(intArrayOf(1))
//        nodeDao.insertAll(node)
        val nodeEntities: List<NodeEntity> = nodeDao.getAll()
//        nodeDao.getRels()
        Log.d("nodes", nodeEntities.toString())
    }
}